package com.banque.demo.web;


import com.banque.demo.dao.*;
import com.banque.demo.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppClientRestController {

    @Autowired
    private ClientRepository clientRepository;


    @Autowired
    private CompteRepository compteRepository;

    @Autowired
    private VirementRepository virementRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private AgentRepository agentRepository;



    //AgentController

    @GetMapping(value="/AgentProfil/{id_agent}/{id_client}/ConsulterComptes")
    public List<Compte> comptes(@PathVariable (name="id_agent") long id_agent, @PathVariable (name="id_client") long id_client){

        Agent agent_actuel=agentRepository.findById(id_agent).get();
        List<Client> clients= (List<Client>) agent_actuel.getClients();
        Client client_actuel=clients.get(Math.toIntExact(id_client));
       // Client client_actuel=clientRepository.findById(id_client).get();
        client_actuel.setAgent(agent_actuel);
       List<Compte> comptes= (List<Compte>) client_actuel.getComptes();
       return comptes;


    }

    @PostMapping(value="/AgentProfil/{id_agent}/{id_client}/AjouterCompte")
    public Compte ajoutercompte(@PathVariable (name="id_agent") long id_agent,@PathVariable (name="id_client") long id_client,@RequestBody Compte compte){

        Agent agent_actuel=agentRepository.findById(id_agent).get();
        List<Client> clients= (List<Client>) agent_actuel.getClients();
        Client client_actuel=clients.get(Math.toIntExact(id_client));
     //   Client client_actuel=clientRepository.findById(id_client).get();
        client_actuel.setAgent(agent_actuel);
        client_actuel.getComptes().add(compte);
        compte.setClient(client_actuel);
        clientRepository.save(client_actuel);
        agentRepository.save(agent_actuel);
        return compteRepository.save(compte);
    }

    @PutMapping(value="/AgentProfil/{id_agent}/{id_client}/ConsulterComptes/{id_compte}")
    public Compte updatecompte(@PathVariable (name="id_agent") long id_agent,@PathVariable (name="id_client") long id_client,@PathVariable (name="id_compte") long id_compte,@RequestBody Compte compte){

        Agent agent_actuel=agentRepository.findById(id_agent).get();
        List<Client> clients= (List<Client>) agent_actuel.getClients();
       Client client_actuel=clients.get(Math.toIntExact(id_client));
      //  Client client_actuel=clientRepository.findById(id_client).get();
        client_actuel.setAgent(agent_actuel);
        client_actuel.getComptes().add(compte);
        compte.setClient(client_actuel);
        compte.setId(id_compte);
        clientRepository.save(client_actuel);
        agentRepository.save(agent_actuel);
        return compteRepository.save(compte);
    }

    @DeleteMapping(value="/ConsulterComptes/{id_compte}")
    public void deletecompte(@PathVariable(name="id_compte") Long id_compte){

        compteRepository.deleteById(id_compte);
    }

    @PostMapping(value="/AgentProfil/{id_agent}/CreerClient")
    public Client ajouterclient(@PathVariable (name="id_agent") long id_agent,@RequestBody Client client){

        Agent agent_actuel=agentRepository.findById(id_agent).get();
        client.setAgent(agent_actuel);
        agent_actuel.getClients().add(client);
        agentRepository.save(agent_actuel);
        return clientRepository.save(client);

    }
    @PutMapping(value="/AgentProfil/{id_agent}/{id_client}/ModifierClient")
    public Client modifierclient(@PathVariable (name="id_agent") long id_agent,@PathVariable (name="id_client") long id_client,@RequestBody Client client){


       Agent agent_actuel=agentRepository.findById(id_agent).get();
        client.setId(id_client);
        client.setAgent(agent_actuel);
        agent_actuel.getClients().add(client);
        agentRepository.save(agent_actuel);
        return clientRepository.save(client);

    }


    @GetMapping(value="/AgentProfil/{id_agent}/ConsulterClients")
    public List<Client> clients(@PathVariable (name="id_agent") long id_agent){

        List<Client> clients= (List<Client>) agentRepository.findById(id_agent).get().getClients();
        return clients;
    }

    @PutMapping(value="/AgentProfil/{id_agent}/ActiverClient/{id_client}")
    public Client activerclient(@PathVariable (name="id_agent") long id_agent,@PathVariable (name="id_client") long id_client,@RequestBody Client client){

        Agent agent_actuel=agentRepository.findById(id_agent).get();
        client.setId(id_client);
        client.setAgent(agent_actuel);
        client.setStatus("active");
        agent_actuel.getClients().add(client);
        agentRepository.save(agent_actuel);
        return clientRepository.save(client);
    }

    @PutMapping(value="/AgentProfil/{id_agent}/SuspendreClient/{id_client}")
    public Client suspendreclient(@PathVariable (name="id_agent") long id_agent,@PathVariable (name="id_client") long id_client,@RequestBody Client client){

        Agent agent_actuel=agentRepository.findById(id_agent).get();
        client.setId(id_client);
        client.setAgent(agent_actuel);
        client.setStatus("suspendu");
        agent_actuel.getClients().add(client);
        agentRepository.save(agent_actuel);
        return clientRepository.save(client);
    }


    @GetMapping(value="/AgentProfil/{id_agent}/ConsulterMessage/{id_client}")
    public List<Message> messages(@PathVariable (name="id_agent") long id_agent,@PathVariable(name="id_client") Long id_client){

        List<Client> clients= (List<Client>) agentRepository.findById(id_agent).get().getClients();
        Client client_actuel=clients.get(Math.toIntExact(id_client));
        List<Message> messages= (List<Message>) client_actuel.getMessages();
        return messages;
    }

    @PostMapping("/Profil/{id_agent}/ContacterClient/{id_client}")
    public Message contacter(@PathVariable (name="id_agent") long id_agent,@PathVariable(name="id_client") Long id_client,@RequestBody Message message) {


        Agent agent_actuel=agentRepository.findById(id_agent).get();
        List<Client> clients= (List<Client>) agent_actuel.getClients();
        Client client_actuel=clients.get(Math.toIntExact(id_client));
        message.setAgent(agent_actuel);
        message.setClient(client_actuel);
        client_actuel.getMessages().add(message);
        agent_actuel.getMessages().add(message);
        return messageRepository.save(message);
    }




    //ClientController

    @GetMapping("/Profil/{id}")
    public Client Profil(@PathVariable(name="id") Long id){

      return  clientRepository.findById(id).get();
    }

    @GetMapping(value="/Profil/{id}/ListComptes")
    public List<Compte> comptes(@PathVariable(name="id") Long id){

         List<Compte> mescomptes= (List<Compte>) clientRepository.findById(id).get().getComptes();
           return mescomptes;

    }

    @GetMapping(value="/Profil/{id_client}/ListComptes/{id_compte}")
    public Compte ConsulterCompte(@PathVariable(name="id_client") Long id_client,@PathVariable(name="id_compte") Long id_compte){

        Client client_actuel=clientRepository.findById(id_client).get();
       List<Compte> mes_comptes= (List<Compte>) client_actuel.getComptes();
          Compte compte_actuel=mes_comptes.get(Math.toIntExact(id_compte));
         return compte_actuel;


    }

    @GetMapping(value="/Profil/{id_client}/ListComptes/{id_compte}/ConsulterSolde")
    public long ConsulterSolde(@PathVariable(name="id_client") Long id_client,@PathVariable(name="id_compte") Long id_compte){

        Client client_actuel=clientRepository.findById(id_client).get();
        List<Compte> mes_comptes= (List<Compte>) client_actuel.getComptes();
        Compte compte_actuel=mes_comptes.get(Math.toIntExact(id_compte));
        long solde_compte_actuel=compte_actuel.getSolde();
        return solde_compte_actuel;
    }


    
   @PostMapping("/Profil/{id_client}/ListComptes/{id_compte}/EffectuerVirement")
    public Virement effectuerVirement(@RequestBody Virement virement,@PathVariable(name="id_client") Long id_client,@PathVariable(name="id_compte") Long id_compte){

        Client client_actuel=clientRepository.findById(id_client).get();
       //debiteur
       List<Compte> mes_comptes= (List<Compte>) client_actuel.getComptes();
       Compte compte_actuel=mes_comptes.get(Math.toIntExact(id_compte));

      //crediteur
       Compte   compte2 = compteRepository.findByNum(virement.getNumcomptebenf());

         virement.Virer(compte_actuel,compte2,virement.getMontant());
         compte_actuel.getVirements().add(virement);
         virement.setClient(client_actuel);
         virement.setCompte(compte_actuel);
         compteRepository.save(compte_actuel);
         compteRepository.save(compte2);

         return  virementRepository.save(virement);



    }

    @GetMapping(value="/Profil/{id_client}/ListComptes/{id_compte}/ConsulterTransactions")
    public List<Virement> virements(@PathVariable(name="id_client") Long id_client,@PathVariable(name="id_compte") Long id_compte){

        Client client_actuel=clientRepository.findById(id_client).get();
         List<Compte> mes_comptes= (List<Compte>) client_actuel.getComptes();
         Compte compte_actuel=mes_comptes.get(Math.toIntExact(id_compte));
         List<Virement> mes_virements= (List<Virement>) compte_actuel.getVirements();
        return mes_virements;
    }


    @PostMapping("/Profil/{id}/ContacterAgent")
    public Message contacter(@RequestBody Message message,@PathVariable(name="id") Long id) {

        Client client_actuel=clientRepository.findById(id).get();
        message.setAgent(client_actuel.getAgent());
        message.setClient(client_actuel);
        client_actuel.getMessages().add(message);
        return messageRepository.save(message);
    }



}
