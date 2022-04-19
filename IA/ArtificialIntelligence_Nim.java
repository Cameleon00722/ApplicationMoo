package com.company.IA;

import com.company.nim.CoupNim;
import com.company.nim.Tas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArtificialIntelligence_Nim implements ArtificialIntelligenceImpl_Nim<CoupNim> {


    public CoupNim IA_retour(int nb_allu_max, int nb_tas, Tas tas) {
        Random aleatoire=new Random();
        List<CoupNim> list=getListAllCoups(nb_allu_max, nb_tas, tas);
        int a=aleatoire.nextInt(list.size());
        return list.get(a);
    }

    private List<CoupNim> getListAllCoups(int nb_allu_max,int nb_tas, Tas tas){
        List<CoupNim> list = new ArrayList<>();
        for(int i=1;i<nb_tas+1;i++){
            for(int j=1;j<nb_allu_max+1;j++){
                CoupNim un_coup=new CoupNim(i,j);
                if(j<tas.nbAllumettes(i)+1){
                    list.add(un_coup);
                }
            }
        }
        return list ;
    }


}
