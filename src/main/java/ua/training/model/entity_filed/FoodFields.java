package ua.training.model.entity_filed;

public enum FoodFields {

    ID("id"),

    ;
    FoodFields(String tableName){
        this.tableName = tableName;
    }
    private String tableName;
    private String fieldName;
}
