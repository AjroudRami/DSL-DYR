package com.polytech.dsl.ssl.utils

import com.polytech.dsl.ssl.core.regression.law.composition.MeanComposition
import com.polytech.dsl.ssl.core.regression.law.composition.SumComposition

class Compositions {

    Compositions() {}

    static sum(){
        return new SumComposition()
    }

    static mean(){
        return new MeanComposition()
    }
}
