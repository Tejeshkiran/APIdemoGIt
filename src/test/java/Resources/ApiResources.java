package Resources;

public enum ApiResources {

    AddPlaceAPI("/maps/api/place/add/json"),
    deletePlaceAPI("/maps/api/place/delete/json"),
    getPlaceAPI("/maps/api/place/get/json");

    String resource;
    ApiResources(String resource) {
        this.resource = resource;
    }

    public String getResource()
    {
        return resource;
    }
}
