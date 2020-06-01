package com.banque.demo.services;


import com.banque.demo.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AppInitServiceImp implements AppInitService {

    @Autowired
    private BanqueRepository banqueRepository;

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CompteRepository compteRepository;

    @Autowired
    private VirementRepository virementRepository;

    @Override
    public void initClients() {
  /*      agentRepository.findAll().forEach(agent -> {
            Stream.of("P2598", "C21578", "Z21547").forEach(cinClient -> {
                Client client = new Client();
                client.setCin(cinClient);
                client.setAgent(agent);
                clientRepository.save(client);
            });
      });
*/

    }

    @Override
    public void initComptes() {
    //     clientRepository.findAll().forEach(c->{


  //      Stream.of(25648729, 25147893, 12489637).forEach(numCompte -> {
     //       Compte compte = new Compte();
       //     compte.setNum(numCompte);
         //   compte.setSlode(5 + (int) (Math.random() * 100));
     //      compte.setClient(clientRepository.);
           // compteRepository.save(compte);


    //    });


      //    });


    }

    @Override
    public void initVirements() {
     /*   clientRepository.findAll().forEach(client->{
            client.getComptes().forEach(compte->{
                Virement virement=new Virement();
                virement.setClient(client);
                virement.setCompte(compte);
                virement.setMontant(3+(int)(Math.random()*7));
                virementRepository.save(virement);
            });
        });*/


    }

    @Override
    public void initAgents() {

        /*    Stream.of("ahmed","mohammed").forEach(nomAgent -> {
                Agent agent = new Agent();
                agent.setNom(nomAgent);
                agentRepository.save(agent);
            });*/


    }

    @Override
    public void initMessages() {

    }

    @Override
    public void initBanques() {

    }

    @Override
    public void initAdmins() {

    }


}
