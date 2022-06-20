package org.isj.ing3.isi.webservice.webservicerest.utils.etats;


import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.apache.pdfbox.multipdf.Overlay;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.util.Matrix;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.*;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Note;
import org.isj.ing3.isi.webservice.webservicerest.service.IEtudiant;
import org.isj.ing3.isi.webservice.webservicerest.serviceImpl.EtudiantServiceImpl;
import org.isj.ing3.isi.webservice.webservicerest.utils.Bd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.awt.*;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;

/**
 * @author USER
 */
@Service
public class GeneratePDF {

    @Autowired
    EtudiantServiceImpl etudiantImpl;

    @Autowired
    IEtudiant iEtudiant;


    public void genererCartesEtudiants(String fil, String speci, int an, int niv) throws Exception {


            // - Chargement et compilation du rapport
            //JasperDesign jasperDesign = JRXmlLoader.load(GeneratePDF.class.getClassLoader().getResourceAsStream("src/main/ressources/etats/carte_etudiant.jrxml"));
            JasperReport jasperReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/etats/carte_etudiant.jrxml"));
            File fichierCourant = null;

                try {
                    long t1 = System.currentTimeMillis();
                    // - Paramètres à envoyer au rapport

                    Connection connection = Bd.getConnection();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMMMM yyyy");
                    /*List<CarteEtudiant> carteEtudiants = new ArrayList<CarteEtudiant>();
                    carteEtudiants.add(etudiant);*/
                    Map parameters = new HashMap();
                    parameters.put("filiere", fil);
                    parameters.put("specialite", speci);
                    parameters.put("niveau", niv);
                    parameters.put("annee", an);

                    // - Execution du rapport
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
                    //JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(carteEtudiants);

                    // - Execution du rapport
                    //JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, jrBeanCollectionDataSource);

                    // - Création du rapport au format PDF
                    String fileName;
                        fileName = "src/main/resources/etats/cartes-" + fil + "-" + speci + "-" + System.currentTimeMillis() + ".pdf";

                    File f = new File(fileName);
                    f.createNewFile();
                    JasperExportManager.exportReportToPdfFile(jasperPrint, f.getAbsolutePath());
                    fichierCourant = f;
                    long t2 = System.currentTimeMillis();
                    System.out.println("Fin d'impression des cartes en..." + (t2 - t1) / 60000 + " min");
                    //openFile(f);
                } catch (Exception e) {
                    e.printStackTrace();
                }
//openFile(fichierCourant);
            File result = superposerPDFCarteEtudiant(new File("src/main/resources/images/fond_carte_etudiant.pdf"), fichierCourant);
            //openFile(result);
    }

    public void genererReleve(List<String> matricules, String classe, int annee, String typeReleve,
                                     String speci,String filiere,int niveau,String decision) throws Exception {

        String listeMatricules = "";
        if (matricules != null && matricules.size() > 0) {
            for (int i = 0; i < matricules.size(); i++) {
                listeMatricules += (matricules.get(i) + ";");
            }
        }
        System.out.println("Matricules:"+listeMatricules);

        JasperDesign jasperDesign = JRXmlLoader.load(GeneratePDF.class.getClassLoader().getResourceAsStream("src/main/resources/etats/ReleveFinal.jrxml"));
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        if (matricules != null && matricules.size() > 0) {

            // - Chargement et compilation du rapport
            File fichierCourant = null;
            Connection connection = Bd.getConnection();
            for (int i = 0; i < matricules.size(); i++) {
                String matricule = matricules.get(i);
                try {
                    long t = System.currentTimeMillis();
                    System.out.println("Debut d'impresion du Relevé " + (i + 1) + " " + matricule);

                    //Etudiant etudiant = new Isj().retrouverEtudiantMatricule(matricule);
                    Etudiant etudiant = new Etudiant();
                    etudiant = iEtudiant.getStudentByMatricule(matricule);
                    long t1 = System.currentTimeMillis();

                    // - Paramètres à envoyer au rapport
                    Map parameters = new HashMap();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMMMM yyyy");
                    parameters.put("filiere", filiere);
                    parameters.put("specialites", filiere + " - " + speci);
                    parameters.put("nom_etudiant", etudiant.getNom() + " " + etudiant.getPrenom());
                    parameters.put("date_naissance", dateFormat.format(etudiant.getDateNaissance()));
                    parameters.put("niveau", niveau);
                    parameters.put("classe", classe);
                    parameters.put("annee_academique", annee);
                    parameters.put("sexe", etudiant.getSexe());
                    parameters.put("matricule", matricule);
                    parameters.put("image_isj", new File("src/main/resources/images/logo_isj.jpeg").getAbsolutePath());
                    parameters.put("image_uy1", new File("src/main/resources/images/logo_uy1.jpg").getAbsolutePath());
                    parameters.put("image_ensp", new File("src/main/resources/images/logo_ensp.jpg").getAbsolutePath());
                    parameters.put("speci", speci);
                    parameters.put("numero_releve", i);
                    System.out.println(etudiant.toString());
                    Long numero = (etudiant.getCode() + annee);
                    System.out.println("Numero = " + numero);
                    parameters.put("numero", numero);
                    parameters.put("type_releve", typeReleve);
                    parameters.put("semestr", typeReleve);
                    parameters.put("listeMatricules", listeMatricules);
                    parameters.put("decision", decision);
                    // - Execution du rapport
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);

                    // - Création du rapport au format PDF
                    String fileName;
                    if (matricules.size() == 1)
                        fileName = "src/main/resources/etats/Releve-" + matricule + "-" + classe + "-" + System.currentTimeMillis() + ".pdf";
                    else
                        fileName = "src/main/resources/etats/Releves" + "-" + classe + "-" + annee + "-" + System.currentTimeMillis() + ".pdf";
                    File f = new File(fileName);
                    f.createNewFile();
                    JasperExportManager.exportReportToPdfFile(jasperPrint, f.getAbsolutePath());
                    if (i == 0) {
                        fichierCourant = f;
                    } else {
                        PDFMergerUtility pdfMergerUtility = new PDFMergerUtility();
                        pdfMergerUtility.addSource(fichierCourant);
                        pdfMergerUtility.addSource(f);
                        //String destinationFinale = pv.getAbsolutePath().substring(0, pv.getAbsolutePath().indexOf(".") - 1) + "-synthese.pdf";
                        //File destinationFinaleFile = new File(destinationFinale);
                        //destinationFinaleFile.createNewFile();
                        pdfMergerUtility.setDestinationFileName(fichierCourant.getAbsolutePath());
                        pdfMergerUtility.mergeDocuments();
                        f.delete();
                    }
                    long t2 = System.currentTimeMillis();
                    System.out.println("Fin d'impression du releve en..." + (t2 - t1) / 60000 + " min");
                    //openFile(f);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            File result = superposerPDF(new File("src/main/resources/images/image_fond.pdf"), fichierCourant);
            //openFile(result);
        }
    }


    public static String getMatriculeFromNote(Note note){

        if(note!=null && note.getEstInscrit()!=null && note.getEstInscrit().getCandidatInscrit()!=null){
            Candidat candidat=note.getEstInscrit().getCandidatInscrit();
            System.out.println(candidat.toString());
            return candidat.toString().substring(candidat.toString().lastIndexOf("-")+1);
        }
        return "";
    }

    static double n;

    public void genererAttestation(List<Note> notes, String classe, int annee, String dateJury,
                                          String speci, String filiere, int niveau, String... mat) throws Exception {

        //System.out.println(notes);
        System.out.println(classe);
        System.out.println(annee);
        System.out.println(dateJury);
        System.out.println(speci);
        System.out.println(filiere);
        System.out.println(niveau);
        //System.out.println(mat[0]);

        String listeMatricules = "";
        if (notes != null && notes.size() > 0) {
            for (int i = 0; i < notes.size(); i++) {
                listeMatricules += (getMatriculeFromNote(notes.get(i)) + ";");
            }
        }else listeMatricules=(mat[0]+";");
        System.out.println("Matricules:"+listeMatricules);

        JasperReport jasperReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/etats/attestationReussite.jrxml"));

        if ((notes != null && notes.size() > 0) || !listeMatricules.equalsIgnoreCase("")) {

            Connection connection = Bd.getConnection();
            // - Chargement et compilation du rapport
            File fichierCourant = null;
            String[] matricules = listeMatricules.split(";");
            for (int i = 0; i < matricules.length; i++) {
                String matricule = matricules[i];
                try {
                    long t = System.currentTimeMillis();
                    System.out.println("Debut d'impresion de l'attestation " + (i + 1) + " " + matricule+" - Note:"+(notes!=null?notes.get(i).getValeurNote():n));

                    //Etudiant etudiant = new Isj().retrouverEtudiantMatricule(matricule);
                    Etudiant etudiant = iEtudiant.getStudentByMatricule(matricule);
                    long t1 = System.currentTimeMillis();

                    // - Paramètres à envoyer au rapport
                    Map parameters = new HashMap();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMMMM yyyy");
                    parameters.put("filiere", filiere);
                    parameters.put("specialites", filiere + " - " + speci);
                    parameters.put("nom_etudiant", etudiant.getNom() + " " + etudiant.getPrenom());
                    parameters.put("date_naissance", dateFormat.format(etudiant.getDateNaissance()));
                    parameters.put("niveau", niveau);
                    parameters.put("classe", classe);
                    parameters.put("annee_academique", annee);
                    parameters.put("sexe", etudiant.getSexe());
                    parameters.put("matricule", matricule);
                    parameters.put("image_isj", new File("src/main/resources/images/logo_isj.jpeg").getAbsolutePath());
                    parameters.put("image_uy1", new File("src/main/resources/images/logo_uy1.jpg").getAbsolutePath());
                    parameters.put("image_ensp", new File("src/main/resources/images/logo_ensp.jpg").getAbsolutePath());
                    parameters.put("speci", speci);
                    parameters.put("numero_releve", i);
                    System.out.println(etudiant.toString());
                    Long numero = (etudiant.getCode() + annee);
                    System.out.println("Numero = " + numero);
                    parameters.put("numero", numero);
                    parameters.put("date_jury", dateJury);
                    parameters.put("listeMatricules", "");
                    parameters.put("type_releve", "Annuel");
                    parameters.put("semestr", "Annuel");
                    parameters.put("note_cycle_licence", notes!=null?notes.get(i).getValeurNote():n);
                    parameters.put("branche", getBranche(speci));
                    // - Execution du rapport
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);

                    // - Création du rapport au format PDF
                    String fileName;
                    if ( matricules.length == 1)
                        fileName = "src/main/resources/etatsImprimes/attestations/attestation" + matricule + "-" + classe + "-" + System.currentTimeMillis() + ".pdf";
                    else
                        fileName = "src/main/resources/etatsImprimes/attestations/attestation" + "-" + classe + "-" + annee + "-" + System.currentTimeMillis() + ".pdf";
                    File f = new File(fileName);
                    f.createNewFile();
                    JasperExportManager.exportReportToPdfFile(jasperPrint, f.getAbsolutePath());
                    if (i == 0) {
                        fichierCourant = f;
                    } else {
                        PDFMergerUtility pdfMergerUtility = new PDFMergerUtility();
                        pdfMergerUtility.addSource(fichierCourant);
                        pdfMergerUtility.addSource(f);
                        //String destinationFinale = pv.getAbsolutePath().substring(0, pv.getAbsolutePath().indexOf(".") - 1) + "-synthese.pdf";
                        //File destinationFinaleFile = new File(destinationFinale);
                        //destinationFinaleFile.createNewFile();
                        pdfMergerUtility.setDestinationFileName(fichierCourant.getAbsolutePath());
                        pdfMergerUtility.mergeDocuments();
                        f.delete();
                    }
                    long t2 = System.currentTimeMillis();
                    System.out.println("Fin d'impression de l'attestation en..." + (t2 - t1) / 60000 + " min");
                    //openFile(f);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            File result = superposerPDF(new File("src/main/resources/images/image_fond.pdf"), fichierCourant);
            //openFile(result);;
        }
    }

    public void genererDiplome(List<Note> notes, String classe, int annee, String dateJury,
                                      String speci, String filiere, int niveau, String... mat) throws Exception {

        System.out.println(notes);
        System.out.println(classe);
        System.out.println(annee);
        System.out.println(dateJury);
        System.out.println(speci);
        System.out.println(filiere);
        System.out.println(niveau);
        //System.out.println(mat[0]);

        String listeMatricules = "";
        if (notes != null && notes.size() > 0) {
            for (int i = 0; i < notes.size(); i++) {
                listeMatricules += (getMatriculeFromNote(notes.get(i)) + ";");
            }
        }else listeMatricules=(mat[0]+";");
        System.out.println("Matricules:"+listeMatricules);


        JasperReport jasperReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/etats/diplome.jrxml"));
        if ((notes != null && notes.size() > 0) || !listeMatricules.equalsIgnoreCase("")) {

           // Connection connection = AbstractFacade.getConnection();
            Connection connection = Bd.getConnection();
            // - Chargement et compilation du rapport
            File fichierCourant = null;
            String[] matricules = listeMatricules.split(";");
            for (int i = 0; i < matricules.length; i++) {
                String matricule = matricules[i];
                try {
                    long t = System.currentTimeMillis();
                    System.out.println("Debut d'impresion du diplome " + (i + 1) + " " + matricule+" - Note:"+(notes!=null?notes.get(i).getValeurNote():n));

                    //Etudiant etudiant = new Isj().retrouverEtudiantMatricule(matricule);
                    Etudiant etudiant = etudiantImpl.getStudentByMatricule(matricule);
                    long t1 = System.currentTimeMillis();

                    // - Paramètres à envoyer au rapport
                    Map parameters = new HashMap();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMMMM yyyy");
                    parameters.put("filiere", filiere);
                    parameters.put("specialites", filiere + " - " + speci);
                    parameters.put("nom_etudiant", etudiant.getNom() + " " + etudiant.getPrenom());
                    parameters.put("date_naissance", dateFormat.format(etudiant.getDateNaissance()));
                    parameters.put("niveau", niveau);
                    parameters.put("classe", classe);
                    parameters.put("annee_academique", annee);
                    parameters.put("sexe", etudiant.getSexe());
                    parameters.put("matricule", matricule);
                    parameters.put("image_isj", new File("images/logo_isj.jpeg").getAbsolutePath());
                    parameters.put("image_uy1", new File("images/logo_uy1.jpg").getAbsolutePath());
                    parameters.put("image_ensp", new File("images/logo_ensp.jpg").getAbsolutePath());
                    parameters.put("speci", speci);
                    parameters.put("numero_releve", i);
                    System.out.println(etudiant.toString());
                    Long numero = (etudiant.getCode() + annee);
                    System.out.println("Numero = " + numero);
                    parameters.put("numero", numero);
                    parameters.put("date_jury", dateJury);
                    parameters.put("listeMatricules", listeMatricules);
                    parameters.put("type_releve", "Annuel");
                    parameters.put("semestr", "Annuel");
                    parameters.put("note_cycle_licence", notes!=null?notes.get(i).getValeurNote():n);
                    parameters.put("branche", getBranche(speci));
                    // - Execution du rapport
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);

                    // - Création du rapport au format PDF
                    String fileName;
                    if ( matricules.length == 1)
                        fileName = "esrc/main/resources/etatsImprimes/diplomes/Diplome-" + matricule + "-" + classe + "-" + System.currentTimeMillis() + ".pdf";
                    else
                        fileName = "src/main/resources/etatsImprimes/diplomes/Diplomes" + "-" + classe + "-" + annee + "-" + System.currentTimeMillis() + ".pdf";
                    File f = new File(fileName);
                    f.createNewFile();
                    JasperExportManager.exportReportToPdfFile(jasperPrint, f.getAbsolutePath());
                    if (i == 0) {
                        fichierCourant = f;
                    } else {
                        PDFMergerUtility pdfMergerUtility = new PDFMergerUtility();
                        pdfMergerUtility.addSource(fichierCourant);
                        pdfMergerUtility.addSource(f);
                        //String destinationFinale = pv.getAbsolutePath().substring(0, pv.getAbsolutePath().indexOf(".") - 1) + "-synthese.pdf";
                        //File destinationFinaleFile = new File(destinationFinale);
                        //destinationFinaleFile.createNewFile();
                        pdfMergerUtility.setDestinationFileName(fichierCourant.getAbsolutePath());
                        pdfMergerUtility.mergeDocuments();
                        f.delete();
                    }
                    long t2 = System.currentTimeMillis();
                    System.out.println("Fin d'impression de du diplome en..." + (t2 - t1) / 60000 + " min");
                    //openFile(f);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            File result = superposerPDF(new File("src/main/resources/images/logo_paysage.pdf"), fichierCourant);
            //openFile(result);;
        }
    }


    public static String getBranche(String specilatite){

        if(specilatite.equalsIgnoreCase("Ingénierie Logicielle")||
                specilatite.equalsIgnoreCase("Management des Systèmes d'Information")||
                specilatite.equalsIgnoreCase("Data Science")||
                specilatite.equalsIgnoreCase("Sécurité des Systèmes d'Information"))
            return "Informatique et Systèmes d'Information";
        else if(specilatite.equalsIgnoreCase("Sécurité des Systèmes et des Communications")||
                specilatite.equalsIgnoreCase("Technologies Mobiles, Systèmes et Services Réseaux"))
            return "Systèmes, Réseaux et Télécommunications";
        else return "";
    }

    public static File superposerPDFCarteEtudiant (File base, File fichier) throws IOException {

        PDDocument overlayDoc = PDDocument.load(base);
        Overlay overlayObj = new Overlay();

        PDDocument originalDoc = PDDocument.load(fichier);
        overlayObj.setOverlayPosition(Overlay.Position.BACKGROUND);
        overlayObj.setInputPDF(originalDoc);
        overlayObj.setAllPagesOverlayPDF(overlayDoc);      //alternatives?
        Map<Integer, String> ovmap = new HashMap<Integer, String>();
        overlayObj.overlay(ovmap);
        //File result=new File("result.pdf");
        originalDoc.save(fichier);

        overlayDoc.close();
        originalDoc.close();

        return fichier;
    }

    public static File superposerPDF(File base, File fichier) throws IOException {

        PDDocument overlayDoc = PDDocument.load(base);
        Overlay overlayObj = new Overlay();

        PDDocument originalDoc = PDDocument.load(fichier);
        overlayObj.setOverlayPosition(Overlay.Position.BACKGROUND);
        overlayObj.setInputPDF(originalDoc);
        overlayObj.setAllPagesOverlayPDF(overlayDoc);      //alternatives?
        Map<Integer, String> ovmap = new HashMap<Integer, String>();
        overlayObj.overlay(ovmap);
        //File result=new File("result.pdf");
        originalDoc.save(fichier);

        overlayDoc.close();
        originalDoc.close();

        return fichier;
    }

    public void genererFicheAbsence(String libelleClasse, Integer niveau, String filiere, Integer
            annee_academique, Date datedebut, Date datefin, String semestre) throws Exception {

        Connection connection = null;

        try {
            long t1 = System.currentTimeMillis();
            connection = Bd.getConnection();
            // - ConnexionController à la base
/*            Driver monDriver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(monDriver);
            //connection = DriverManager.getConnection(url, login, password);*/
            //connection = AbstractFacade.getConnection();

/*            String path="images/logo_isj.jpeg";
            System.out.println(new File(path).getAbsolutePath());
            System.out.println(new File(path).getPath());
            System.out.println(new File(path).getCanonicalPath());
            System.out.println(new File(path).getAbsoluteFile().getCanonicalPath());
            System.out.println(new File(path).getAbsoluteFile().getPath());
            System.out.println(new File(path).getAbsoluteFile().getAbsoluteFile());*/

            // - Chargement et compilation du rapport

            JasperReport jasperReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/etats/FicheAbsences2.jrxml"));

            // - Paramètres à envoyer au rapport
            Map parameters = new HashMap();
            parameters.put("filiere", filiere);
            parameters.put("niveau", niveau);
            parameters.put("annee_academique", annee_academique);
            parameters.put("semestre", semestre);
            parameters.put("date_debut", datedebut);
            parameters.put("date_fin", datefin);
            parameters.put("classe", libelleClasse);
            parameters.put("logo_isj", new File("src/main/resources/images/logo_isj.jpeg").getAbsolutePath());

            // - Execution du rapport
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
            // - Création du rapport au format PDF
            String destination = new File("src/main/resources/etatsImprimes/absences/" + "Fiche " + filiere + "-" + niveau + "-" + semestre + "-" + System.currentTimeMillis() + ".pdf").getAbsolutePath();
            File f = new File(destination);
            f.createNewFile();
            System.out.println("Generation de l'etat..." + destination);
            JasperExportManager.exportReportToPdfFile(jasperPrint, destination);
            System.out.println("Etat Genere...");

            long t2 = System.currentTimeMillis();

            System.out.println("Fin d'impression de la fiche en..." + (t2 - t1) / 60000 + " min");
            //openFile(f);
            //connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void genererPv(Integer annee_academique, String semestre, String classe, String filiere, String
            specialites, boolean formatPDF,int niv) throws Exception {
        Integer niveau=niv;
        /*if(classe.endsWith("1A")||classe.endsWith("1B"))
            niveau = 1;
else
    niveau = Integer.parseInt(String.valueOf(classe.charAt(classe.length() - 1)));
        //String filiere=classe.toUpperCase().startsWith("ING")?"Ingenieur":"Licence";*/

        try {
            Connection connection = null;
            // - ConnexionController à la base
/*            Driver monDriver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(monDriver);
            //connection = DriverManager.getConnection(url, login, password);*/
           // connection = AbstractFacade.getConnection();
            connection = Bd.getConnection();
            File pv = imprimerPV(niveau, filiere, annee_academique, semestre, connection, classe, specialites,formatPDF);
            File recapitulatif = imprimerRecapitulatif(niveau, filiere, annee_academique, semestre, connection, classe, specialites,formatPDF);

            if(formatPDF){
                PDFMergerUtility pdfMergerUtility = new PDFMergerUtility();
                pdfMergerUtility.addSource(pv);
                pdfMergerUtility.addSource(recapitulatif);
                String destinationFinale = pv.getAbsolutePath().substring(0, pv.getAbsolutePath().indexOf(".") - 1) + "-synthese.pdf";
                File destinationFinaleFile = new File(destinationFinale);
                destinationFinaleFile.createNewFile();
                pdfMergerUtility.setDestinationFileName(destinationFinale);
                pdfMergerUtility.mergeDocuments();
                ajouterNumerosPages(destinationFinaleFile);
                openFile(destinationFinaleFile);
            }
            else{
                String destinationFinale = pv.getAbsolutePath().substring(0, pv.getAbsolutePath().indexOf(".") - 1) + "-synthese.xlsx";
                File destinationFinaleFile = new File(destinationFinale);
                destinationFinaleFile.createNewFile();
                String[] files = new String[] {pv.getAbsolutePath(),recapitulatif.getAbsolutePath()};
                XSSFWorkbook workbook = new XSSFWorkbook();
                //XSSFSheet sheet = createSheetWithHeader(workbook,"PVSynthese");
                XSSFSheet sheet = workbook.createSheet();

                DataFormat format = workbook.createDataFormat();
                CellStyle styleNumeric = workbook.createCellStyle();
                styleNumeric.setDataFormat(format.getFormat("##.##"));

                try {
                    for (int f = 0; f < files.length; f++) {
                        String file = files[f];
                        FileInputStream inputStream = new FileInputStream(file);
                        XSSFWorkbook tempWorkbook = new XSSFWorkbook(inputStream);

                        int numOfSheets = tempWorkbook.getNumberOfSheets();

                        for (int i = 0; i < numOfSheets; i++) {
                            XSSFSheet tempSheet = tempWorkbook.getSheetAt(i);

                            int indexLastDataInserted = sheet.getLastRowNum();
                            int firstDataRow = getFirstDataRow(tempSheet);

                            Iterator<Row> itRow = tempSheet.rowIterator();

                            while(itRow.hasNext()) {
                                Row tempRow = itRow.next();
                                indexLastDataInserted=indexLastDataInserted + 1;
                                if (tempRow.getRowNum() >= firstDataRow) {
                                    XSSFRow row = sheet.createRow(indexLastDataInserted );

                                    Iterator<Cell> itCell = tempRow.cellIterator();

                                    while(itCell.hasNext()) {
                                        Cell tempCell = itCell.next();
                                        XSSFCell cell = row.createCell(tempCell.getColumnIndex());
                                        //At this point you will have to set the value of the cell depending on the type of data it is
                                        switch (tempCell.getCellType()) {
                                            case 0:
                                                cell.setCellType(0);
                                                cell.setCellValue(tempCell.getNumericCellValue());
                                                break;
                                            case 1:
                                                cell.setCellValue(tempCell.getStringCellValue());
                                                break;
                                            /**
                                             * Add your other types, here is your problem!!!!!
                                             */
                                        }
                                    }
                                }
                            }
                        }
                    }
                }catch (IOException ex1) {
                    System.out.println("Error reading file");
                    ex1.printStackTrace();
                }

                try  {
                    FileOutputStream outputStream = new FileOutputStream(destinationFinaleFile);
                    workbook.write(outputStream);
                    outputStream.close();
                    //workbook.close();
                }catch (Exception e) {
                    e.printStackTrace();
                }
                //openFile(destinationFinaleFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * If the tab has a filter, it returns the row index of the filter + 1, otherwise it returns 0
     * @param tempSheet
     * @return index of first data row
     */
    public static Integer getFirstDataRow(XSSFSheet tempSheet) {
        Integer result = 0;
        Boolean isAutoFilter = tempSheet.getCTWorksheet().isSetAutoFilter();

        if (isAutoFilter) {
            String autoFilterRef = tempSheet.getCTWorksheet().getAutoFilter().getRef();

            result = new CellReference(autoFilterRef.substring(0, autoFilterRef.indexOf(":"))).getRow() + 1;
        }
        return result;
    }

    public static XSSFSheet createSheetWithHeader(XSSFWorkbook workbook,String sheetName){
        XSSFSheet sheet = workbook.createSheet(sheetName);

        /*//Implement the header
    [...]*/

        return sheet;
    }

    public static void ajouterNumerosPages(File destinationFinaleFile) throws IOException {

        PDDocument document = PDDocument.load(destinationFinaleFile);
// get all number of pages.
        int numberOfPages = document.getNumberOfPages();

        for (int i = 0; i < numberOfPages; i++) {
            PDPage fpage = document.getPage(i);

            // calculate to center of the page
            int rotation = fpage.getRotation();
            boolean rotate = rotation == 90 || rotation == 270;
            float pageWidth = rotate ? fpage.getMediaBox().getHeight() : fpage.getMediaBox().getWidth();
            float pageHeight = rotate ? fpage.getMediaBox().getWidth() : fpage.getMediaBox().getHeight();
            float centerX = rotate ? pageHeight / 2f : (pageWidth) / 2f;
            float centerY = rotate ? (pageWidth) / 2f : pageHeight / 2f;

// content stream to write content in pdf page.
            PDPageContentStream contentStream = new PDPageContentStream(document, fpage, PDPageContentStream.AppendMode.APPEND, true);
            contentStream.beginText();
            if (rotate) {
                // rotate the text according to the page rotation
                contentStream.setTextMatrix(Matrix.getRotateInstance(Math.PI / 2, centerX, centerY));
            } else {
                contentStream.setTextMatrix(Matrix.getTranslateInstance(centerX, centerY));
            }
            PDFont font = PDType1Font.HELVETICA_BOLD;
            float fontSize = 10.0f;
            contentStream.setFont(font, fontSize);
            // set text color to red
            contentStream.setNonStrokingColor(0, 0, 0);
            // contentStream.showText("Page "+(i+1)+"/"+numberOfPages);
            contentStream.endText();

           /* PDStreamUtils.write(contentStream,
                    "Page " + (i + 1) + "/" + numberOfPages,
                    PDType1Font.HELVETICA,
                    10,
                    600,
                    15,
                    new Color(0, 0, 0));*///set stayle and size*/
            contentStream.close();
        }
        document.save(destinationFinaleFile);
    }

    private static File imprimerPV(Integer niveau, String filiere, Integer annee_academique, String
            semestre, Connection connection, String classe, String specialites,boolean formatPDF) throws JRException, IOException {

        long t1 = System.currentTimeMillis();
        System.out.println("Début d'impression du PV..." + t1);

        // - Chargement et compilation du rapport
//A partir de l'IDE
        //Pour le PV classique
        //JasperDesign jasperDesign = JRXmlLoader.load("F:\\Institut Saint Jean\\Stages\\Offres de Stage\\Gestion de Notes ISJ\\Gestion_Notes_Final-master\\Gestion_Notes_Final-master\\src\\main\\resources\\etats\\PvFinal.jrxml");

        //Pour le PV synthese
        //JasperDesign jasperDesign = JRXmlLoader.load("F:\\Institut Saint Jean\\Stages\\Offres de Stage\\Gestion de Notes ISJ\\Gestion_Notes_Final-master\\Gestion_Notes_Final-master\\src\\main\\resources\\etats\\PV_Synthese.jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/etats/PvFinal.jrxml"));
        org.eclipse.jdt.internal.compiler.Compiler j;

        // - Paramètres à envoyer au rapport
        Map parameters = new HashMap();
        parameters.put("filiere", filiere);
        parameters.put("niveau", niveau);
        parameters.put("annee_academique", annee_academique);
        parameters.put("semestre", semestre);
        parameters.put("specialites", specialites);
        parameters.put("image_isj", new File("src/main/resources/images/logo_isj.jpeg").getAbsolutePath());

        //Pour le PV synthese
        parameters.put("annee", annee_academique);
        parameters.put("classe", classe);

        // - Execution du rapport
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
        File f;
        if(formatPDF){
                // - Création du rapport au format PDF
            f = new File("src/main/resources/etatsImprimes/pv/" + "PV-" + filiere + "-" + specialites + "-" + niveau + "-" + semestre + "-" + System.currentTimeMillis() + ".pdf");
            f.createNewFile();
            JasperExportManager.exportReportToPdfFile(jasperPrint, f.getAbsolutePath());
        }
        else{
            /*JasperViewer viewer = new JasperViewer(jasperPrint, false);
            viewer.setVisible(true);*//*
            JFrame frame = new JFrame("Jasper report");
            JRViewer viewer = new JRViewer(jasperPrint);
            frame.add(viewer);
            frame.setSize(new Dimension(500, 400));
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
            net.sf.jasperreports.engine.export.JRXlsExporter h;*/

            //Exportation de l'état au format EXCEL
            f = new File("src/main/resources/etatsImprimes/pv/" + "PV-" + filiere + "-" + specialites + "-" + niveau + "-" + semestre + "-" + System.currentTimeMillis() + ".xlsx");
            f.createNewFile();
            SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
            configuration.setOnePagePerSheet(false);
            configuration.setIgnoreGraphics(false);

            File outputFile = new File(f.getAbsolutePath());
            try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                 OutputStream fileOutputStream = new FileOutputStream(outputFile)) {
                Exporter exporter = new JRXlsxExporter();
                exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
                exporter.setConfiguration(configuration);
                exporter.exportReport();
                byteArrayOutputStream.writeTo(fileOutputStream);
            }
        }

        long t2 = System.currentTimeMillis();
        System.out.println("Fin d'impression du PV en..." + ((t2 - t1) / 1000) + " sec");

        return f;

    }

    public void imprimerNotes(int niveau, String filiere, String annee_academique, String semestre,
            String specialite, String typeNote, int anneeDebut, String codeUE) throws JRException, IOException, SQLException {

        long t1 = System.currentTimeMillis();
        System.out.println("Début d'impression de notes..." + t1);
        Connection connection = Bd.getConnection();
        //if(connection==null)
            //connection = AbstractFacade.getConnection();        // - Chargement et compilation du rapport
        //A partir du Jar

        JasperReport jasperReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/etats/NotesUe.jrxml"));
        // - Paramètres à envoyer au rapport
        Map parameters = new HashMap();
        parameters.put("filiere", filiere);
        parameters.put("specialite", specialite);
        parameters.put("niveau", niveau);
        parameters.put("annee_academique", annee_academique);
        parameters.put("typeEval", typeNote);
        parameters.put("image_isj", new File("src/main/resources/images/logo_isj.jpeg").getAbsolutePath());
        parameters.put("annee_debut", anneeDebut);
        parameters.put("semestre", semestre);
        parameters.put("ue", codeUE);

        // - Execution du rapport
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);

        // - Création du rapport au format PDF
        File f = new File("src/main/resources/etatsImprimes/notes/" + "Notes-" + filiere + "-" + specialite + "-" + niveau + "-" + semestre + "-" + System.currentTimeMillis() + ".pdf");
        f.createNewFile();
        JasperExportManager.exportReportToPdfFile(jasperPrint, f.getAbsolutePath());
        long t2 = System.currentTimeMillis();
        System.out.println("Fin d'impression du PV en..." + ((t2 - t1) / 1000) + " sec");
        //openFile(f);
    }

    private static File imprimerRecapitulatif(Integer niveau, String filiere, Integer annee_academique, String
            semestre, Connection connection, String classe, String specialites,boolean formatPDF) throws JRException, IOException {

        long t1 = System.currentTimeMillis();
        System.out.println("Début d'impression du recapitulatif..." + t1);
        // - Chargement et compilation du rapport
        //A partir du Jar
        //A partir de l'IDE
        //Pour le PV classique
        //JasperDesign jasperDesign = JRXmlLoader.load("F:\\Institut Saint Jean\\Stages\\Offres de Stage\\Gestion de Notes ISJ\\Gestion_Notes_Final-master\\Gestion_Notes_Final-master\\src\\main\\resources\\etats\\PV_Synthese.jrxml");

        //Pour le PV synthese
        //JasperDesign jasperDesign = JRXmlLoader.load("F:\\Institut Saint Jean\\Stages\\Offres de Stage\\Gestion de Notes ISJ\\Gestion_Notes_Final-master\\Gestion_Notes_Final-master\\src\\main\\resources\\etats\\PV_Synthese.jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/etats/PV_Synthese.jrxml"));
        org.eclipse.jdt.internal.compiler.Compiler j;

        // - Paramètres à envoyer au rapport
        Map parameters = new HashMap();
        parameters.put("filiere", filiere);
        parameters.put("niveau", niveau);
        parameters.put("annee_academique", annee_academique);
        parameters.put("semestre", semestre);
        parameters.put("specialites", specialites);
        parameters.put("image_isj", new File("src/main/resources/images/logo_isj.jpeg").getAbsolutePath());

        //Pour le PV synthese
        parameters.put("annee", annee_academique);
        parameters.put("classe", classe);

        // - Execution du rapport
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
        File f;
        if(formatPDF){
        // - Création du rapport au format PDF
         f = new File("src/main/resources/etatsImprimes/pv/" + "recapitulatif-" + filiere + "-" + niveau + "-" + semestre + "-" + System.currentTimeMillis() + ".pdf");
        f.createNewFile();
        JasperExportManager.exportReportToPdfFile(jasperPrint, f.getAbsolutePath());

        }
        else{
            f = new File("src/main/resources/etatsImprimes/pv/" + "recapitulatif-" + filiere + "-" + specialites + "-" + niveau + "-" + semestre + "-" + System.currentTimeMillis() + ".xlsx");
            f.createNewFile();
            SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
            configuration.setOnePagePerSheet(false);
            configuration.setIgnoreGraphics(false);

            File outputFile = new File(f.getAbsolutePath());
            try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                 OutputStream fileOutputStream = new FileOutputStream(outputFile)) {
                Exporter exporter = new JRXlsxExporter();
                exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
                exporter.setConfiguration(configuration);
                exporter.exportReport();
                byteArrayOutputStream.writeTo(fileOutputStream);
            }
        }
        long t2 = System.currentTimeMillis();
        System.out.println("Fin d'impression du recapitulatif en..." + ((t2 - t1) / 1000) + " sec");
        return f;
    }

    public static void openFile(File file) {
        if (file.exists()) {

            if (Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().open(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Awt Desktop is not supported!");
                Process p = null;
                try {
                    //p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + file.getAbsolutePath());
                    //p.waitFor();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } else {
            System.out.println("Le fichier " + file.getAbsolutePath() + " n'existe pas !");
        }

        System.out.println("Done");
    }


/*
    public static void main(String[] args) throws Exception {

        //long timer= (long) (1579791257166.0-1579783528456.0);

        Properties properties = new Isj().readSettingApplication();
        new UtilisateurFacade().setProperties((Map) properties);
        utilisateurCourant = new Connexion().connect("emmanuel", "christus10");

        //new Isj().importerEstInscrit("/Users/mac/Downloads/est-Inscrit-INP2133-INP3195.xlsx");
        */
/*new Isj().importerEstInscrit("/Users/mac/Downloads/Est_inscrit_2021_lic2.xlsx");
        new Isj().importerEstInscrit("/Users/mac/Downloads/Est_inscrit_2021_lic3.xlsx");*//*


        //new Isj().sauvegarderEnseignement("/Users/mac/Downloads/Enseignement.xlsx");
        //new Isj().importerTypeEvaluation("/Users/mac/Downloads/type_evaluation.xlsx");
        //new Isj().importerEstInscrit("/Users/mac/Downloads/est-Inscrit-INP2133-INP3195.xlsx");
        //new Isj().importerEvaluation("/Users/mac/Downloads/Evaluation-INP2133-INP3195.xlsx");
        //genererCartesEtudiants("Licence professionnelle","Concepteur Développeur d’Applications pour l’Economie Numérique",
        //        2019,3);

       // genererPv(2019, "Semestre 2","Lic 2","Licence Professionnelle","Concepteur Développeur d’Applications pour l’Economie Numérique");

        //genererPv(2019, "Semestre 1","Ing 3","Ingenieur","Tronc Commun de Branche");
        //ajouterNumerosPages(new File("F:\\Institut Saint Jean\\Stages\\Offres de Stage\\Gestion de Notes ISJ\\Gestion_Notes_Final-master\\Gestion_Notes_Final-master\\etats\\PV-Ingenieur-2-Semestre 1-159509510596-synthese.pdf"));
        List<String> matricules = new ArrayList<>();
       // String mat="1617I03;1617I05;1617I06;1617I07;1617I22;1617I58;1617I53;1617I34;1617I39;1617I40;1617I01;1617I02;1617I04;1617I08;1617I09;1617I14;1617I16;1617I17;1819I115;1617I20;1617I21;1617I24;1819i116;1617I56;1617I57;1617I55;1617I54;1617I51;1617I52;1617I46;1617I50;1617I42;1819I112;1617I41;1617I25;1617I26;1617I27;1617I28;1617i29;1617I30;1819I111;1617I36;1617I38;";
        */
/*String mat="1819L001";
        String[] tabMatricules=mat.split(";");
        for(int i=0;i<tabMatricules.length;i++)
            matricules.add(tabMatricules[i]);
        genererReleve(matricules, "Lic 2", 2019, "Annuel",
                "Concepteur Développeur d’Applications pour l’Economie Numérique",
                "Licence Professionnelle",
                2);*//*


*/
/*genererAttestation(matricules, "Lic 3", 2019, "09/08/2020",
                "Concepteur Développeur d’Applications pour l’Economie Numérique",
                "Licence Professionnelle",
                3);*//*
*/
/*

        *//*

*/
/*genererReleve(matricules, "Lic 3", 2019, "Annuel",
                "Concepteur Développeur d’Applications pour l’Economie Numérique",
                "Licence Professionnelle",
                3);*//*
*/
/*

        *//*

*/
/*new GeneratePDF().imprimerNotes(1,
                "Ingenieur",
                "2019/2020",
                "Semestre 1",
                null,
                "Tronc commun",
                "CC",
                2019,
                "MAT1011"
                );*//*


    }
*/


   // private static final String FILE_NAME = "/Users/mac/Documents/Projets Livrés/Symphonie de l'Epouse/Symphonie de l'Epouse 2è Version Emmanuel-4.xls";

  /*  public static void main(String[] args) {
        try {

            Properties properties = new Isj().readSettingApplication();
            new UtilisateurFacade().setProperties((Map) properties);

            n=15.0;
            genererAttestation(null, "INGMSI 5", 2021, "",
                    "Management des Systèmes d'Information",
                    "Ingenieur",![](../../../../../../../../../resources/etats/._logo_ensp.jpg)
                    5,"1617I54");


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String normalize(String s) {
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        s=s.replaceAll(" ","_");
        s=s.replaceAll("'","_");
        s=s.replaceAll("’","_");
        s=s.replace(".","");
        return s;
    }*/
}
