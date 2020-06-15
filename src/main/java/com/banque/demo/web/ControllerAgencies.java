package com.banque.demo.web;

import com.banque.demo.dao.AgenceRepository;
import com.banque.demo.dao.AgentRepository;
import com.banque.demo.entities.Agence;
import com.banque.demo.entities.Agent;
import com.banque.demo.services.IMail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class ControllerAgencies {
    Logger logger= LoggerFactory.getLogger(ControllerAgencies.class);

    @Autowired
     private IMail iMail;
    @Autowired
    private AgenceRepository agenceRepository;
    @Autowired
    private AgentRepository agentRepository;
    private PasswordEncoder passwordEncoder;

    public ControllerAgencies(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/addNewAgence")
public String addNewAgence(Model model){
        List<Agence> agences=agenceRepository.findAll();
        model.addAttribute("agences",agences);
        model.addAttribute("agent",new Agent());
    return "addNewAgence";
}
@PostMapping("/saveNewAgent")
public String saveNewAgent(Model model, @ModelAttribute("agent") Agent agent,
                           @ModelAttribute("nomAgence") String nomAgence, Principal principal){
    System.out.println(agent);

    Agence agence=agenceRepository.findAgenceByNomAgence(nomAgence);
    agent.setAgence(agence);
    agent.setRole("AGENT");
    System.out.println("Nom Agence Dans l'ajout: "+nomAgence);
    iMail.sendEmail(agent.getEmail(),agent.getUsername(),agent.getPassword());
    agent.setPassword(passwordEncoder.encode(agent.getPassword()));
    agentRepository.save(agent);

    agenceRepository.updateAgence(agence.getId(),agentRepository.findAgentByAgence(agence).size());
logger.debug("un agent nommé "+agent.getNomAgent()+" vient d'etre créer par l'admin "+principal.getName());

    return "redirect:/addNewAgence";

}
@GetMapping("/myAgents")
public String myAgents(Model model){
        try {
            List<Agent>agentList=agentRepository.findAll();
            model.addAttribute("listAgents",agentList);
        }
        catch (Exception e){
            return "redirect:/";
        }

        return "/myAgents";
}
@GetMapping("/deleteAgent")
public String deleteAgent(@RequestParam("id") Long id,Principal principal){
        Agent agent=agentRepository.findById(id).get();
        Agence agence=agenceRepository.findAgenceByAgentsContains(agent);
        agentRepository.delete(agent);
        agenceRepository.updateAgence(agence.getId(),agentRepository.findAgentByAgence(agence).size());
        logger.debug("l'agent :"+agent.getNomAgent()+"  vient detre supprimer par l'admin : "+principal.getName());
        return "redirect:/admin/myAgents";
}
@GetMapping("/editAgent")
    public String editAgent(@RequestParam("id") Long id,Model model){
        model.addAttribute("agent",agentRepository.findById(id).get());
        model.addAttribute("agences",agenceRepository.findAll());

        return "editAgent";

}
@PostMapping("/editAg")
    public String editAg(@ModelAttribute("agent") Agent agent,
                         @RequestParam("id") Long id,@ModelAttribute("nomAgence")
                         String nomAgence,Principal principal){
        Agent oldAgent=agentRepository.findById(id).get();
    System.out.println(" le NOm : "+nomAgence);
    System.out.println("nom 2 :"+agent.getAgence());
    Agence oldAgence =oldAgent.getAgence();
    System.out.println("nombre Agent"+oldAgence.getNomAgence());
    oldAgence.setNombreAgent(oldAgence.getNombreAgent()-1);
    agentRepository.save(oldAgent);
    Agence agence=agenceRepository.findAgenceByNomAgence(nomAgence);
    agence.setNombreAgent(agence.getNombreAgent()+1);
        oldAgent.setAgence(agence);
        oldAgent.setUsername(agent.getUsername());
        oldAgent.setPassword(agent.getPassword());
        oldAgent.setEmail(agent.getEmail());
        oldAgent.setNomAgent(agent.getNomAgent());
        oldAgent.setAdresse(agent.getAdresse());
        oldAgent.setTelephone(agent.getTelephone());
        agentRepository.save(oldAgent);
    logger.debug("l'agent : "+agent.getNomAgent()+" vient detre modifier par l'admin : "+principal.getName());

    return "redirect:/admin/myAgents";
}
}

