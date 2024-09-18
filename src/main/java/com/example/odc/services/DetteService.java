package com.example.odc.services;

import com.example.odc.entities.Dette;

public interface DetteService extends IService<Dette> {
    public double findMontantDu(int id);
    public double findMontantVerser(int id);
}
