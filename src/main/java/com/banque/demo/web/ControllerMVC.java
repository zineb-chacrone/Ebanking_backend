package com.banque.demo.web;

import com.banque.demo.dao.AgenceRepository;
import com.banque.demo.dao.AgentRepository;
import com.banque.demo.dao.BanqueRepository;
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
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class ControllerMVC {

    Logger logger= LoggerFactory.getLogger(ControllerMVC.class);
    @Autowired
private IMail iMail;
    Agence currentAgency=new Agence();
    @Autowired
    private AgenceRepository agenceRepository;
    @Autowired
    private BanqueRepository banqueRepository;
    @Autowired
    private AgentRepository agentRepository;
    private PasswordEncoder passwordEncoder;

    public ControllerMVC(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/admin")
    public String index(Model model){
        return "index";
    }

    @GetMapping("/newAgency")
    public String addAgence(Model model){
        return "addAgence";

    }

   @PostMapping("/add")
    public String submitForm(@ModelAttribute("agence") Agence agence, Principal principal)
    {
        agence.setDateCreation(new Date());
        currentAgency=agence;
        agenceRepository.save(agence);
        System.out.println("coucou");
        logger.debug("une agence vient d'etre créée nommée "+agence.getNomAgence()+" par l'" +
                "dmin "+principal.getName());

        return "addAgent";
    }

    @PostMapping("saveAgent")
    public String saveAgent(@ModelAttribute("agent") Agent agent,Model model,Principal principal){
        System.out.println("Aziz ");
    agent.setAgence(currentAgency);
agent.setRole("AGENT");
    agent.setActivated(true);
        iMail.sendEmail(agent.getEmail(),agent.getUsername(),agent.getPassword());
    agent.setPassword(passwordEncoder.encode(agent.getPassword()));
    ;
    agentRepository.save(agent);
        agenceRepository.updateAgence(currentAgency.getId(),agentRepository.findAgentByAgence(currentAgency).size());

logger.debug("un agent vient d'etre crée nommé :"+agent.getNomAgent()+" par l'admin :"+principal.getName());



        return "addAgent";
    }
@GetMapping("/makeItTrue")
    public String makeItTrue(Model model,@RequestParam(name="bol") Boolean bol) {
    System.out.println("boool"+ bol);
        model.addAttribute("check", bol);
        return "/addAgent";
}

    @GetMapping("/myAgencies")
    public String myAgencies(Model model){
        List<Agence> agenceList =agenceRepository.findAll();
        model.addAttribute("listAgency",agenceList);

        return "myAgencies";
    }
    @GetMapping("/deleteAgency")
    public String deleteAgency(@RequestParam("id") Long id,Principal principal){
        Agence agence=agenceRepository.findById(id).get();
        for(Agent agent:agence.getAgents()){
            agentRepository.delete(agent);
        }
        agenceRepository.delete(agence);
        logger.debug("l'agence : "+agence.getNomAgence()+" vient d'etre supprimer  par " +
                "l'admin : "+principal.getName());

        return "redirect:myAgencies";

    }
    @GetMapping("/editAgency")
    public  String editAgency(@RequestParam("id") Long id,Model model,Principal principal){
        Agence agence=agenceRepository.findById(id).get();
        model.addAttribute("agence",agence);

        return "editAgency";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute("agence") Agence agence,@RequestParam("id") Long id,Principal principal){
       // agenceRepository.update(id,agence);
        Agence oldAgence=agenceRepository.findById(id).get();
        oldAgence.setNomAgence(agence.getNomAgence());
        oldAgence.setVille(agence.getVille());
        oldAgence.setId(id);
        oldAgence.setAdresseAgence(agence.getAdresseAgence());
        agenceRepository.save(oldAgence);
        System.out.println("nom : "+agence.getNomAgence());
        System.out.println("id agence"+agence.getId());
        System.out.println("ID transféré : "+id);
        logger.debug("l'agence : "+agence.getNomAgence()+" vient d'etre modifier  par " +
                "l'admin : "+principal.getName());
        return "redirect:/myAgencies";
    }

}
