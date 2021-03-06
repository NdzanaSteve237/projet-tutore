package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Filiere;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Module;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.repositories.ModuleRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.UtilisateurRepository;
import org.isj.ing3.isi.webservice.webservicerest.service.IModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModuleImpl implements IModule {
    @Autowired
    ModuleRepository moduleRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public int saveModule(Module module) throws IsjException{

        Utilisateur createur = utilisateurRepository.findById(module.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = utilisateurRepository.findById(module.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        module.setCreateur(createur);
        module.setModificateur(modificateur);

        return moduleRepository.save(module).getCode().intValue();
    }

    @Override
    public List<Module> listModules() {
        return moduleRepository.findAll();
    }

    @Override
    public int deleteModule(Long code) throws IsjException {
        moduleRepository.deleteById(getModuleByCode(code).getCode());
        return 1;
    }

    @Override
    public Module getModuleByCode(Long code) throws IsjException {

        return moduleRepository.findById(code).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
    }

    @Override
    public List<Module> getModuleByCodeModule(String codeModule) throws IsjException {
        return moduleRepository.retrouverModule(codeModule).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND)) ;   }
}
