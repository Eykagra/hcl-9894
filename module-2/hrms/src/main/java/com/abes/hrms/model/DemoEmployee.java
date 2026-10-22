package com.abes.hrms.model;

import com.abes.hrms.repository.EmpRepImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DemoEmployee {

    @Autowired
    private EmpRepImp emprepo;

    public int add() {
        return emprepo.add(20, 40);
    }

    public double squareRoot() {
        return emprepo.squareroot(2000);
    }
}
