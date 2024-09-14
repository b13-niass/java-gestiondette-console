package com.example.odc.services;

import com.example.odc.entities.impl.Client;
import com.example.odc.entities.impl.Dette;
import com.example.odc.entities.impl.Paiement;

import java.util.List;

public interface ClientService {
    public void payDebt(Dette dette, int amount);
    public List<Dette> getDette(Client client);
    public List<Paiement> getPaiement(Dette dette);
}
