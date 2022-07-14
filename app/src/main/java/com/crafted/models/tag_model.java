package com.crafted.models;

public enum tag_model {
    SANITARY,
    WOOD,
    METAL,
    ELECTRIC,
    MOVING,
    PAINTER,
    RENOVATION,
    GARDENING,
    MONTAGE;

    public static tag_model getEnumOf(String name){
        switch (name){
            case "Sanitär":
                return tag_model.SANITARY;
            case "Holz":
                return tag_model.WOOD;
            case "Metall":
                return tag_model.METAL;
            case "Elektrik":
                return tag_model.ELECTRIC;
            case "Malerei":
                return tag_model.PAINTER;
            case "Montage":
                return tag_model.MONTAGE;
            case "Umzug":
                return tag_model.MOVING;
            case "Garten":
                return tag_model.GARDENING;
            case "Renovierung":
                return  tag_model.RENOVATION;
        }
        return null;
    }

    @Override
    public String toString(){
        switch (this){
            case SANITARY:
                return  "Sanitär";
            case WOOD:
                return  "Holz";
            case METAL:
                return  "Metall";
            case ELECTRIC:
                return "Elektrik";
            case PAINTER:
                return "Malerei";
            case MONTAGE:
                return "Montage";
            case MOVING:
                return  "Umzug";
            case GARDENING:
                return  "Garten";
            case RENOVATION:
                return "Renovierung" ;
        }
        return null;

    }
}
