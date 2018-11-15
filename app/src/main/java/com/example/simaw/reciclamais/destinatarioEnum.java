package com.example.simaw.reciclamais;

public enum destinatarioEnum {
    ENUM1("Selecione uma opção"),
    ENUM2("Recicla+"),
    ENUM3("Centro de reciclagem"),
    ENUM4("Catador");

    private String destinatarioName;

    private destinatarioEnum(String destinatarioName){
        this.destinatarioName = destinatarioName;
    }

    @Override public String toString(){
        return destinatarioName;
    }
}
