package com.example.odc.repositories;

import com.example.odc.entities.impl.*;

import java.util.List;

public interface ClientRepository {
    public void payDebt(Dette dette, int amount);
    public List<Dette> getDette(Client client);
    public List<Paiement> getPaiement(Dette dette);
}