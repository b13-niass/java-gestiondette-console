package com.example.odc.repositories;

import com.example.odc.entities.Dette;

public interface DetteRepository extends Repository<Dette> {
    public double findMontantDu(int id);
    public double findMontantVerser(int id);
}
