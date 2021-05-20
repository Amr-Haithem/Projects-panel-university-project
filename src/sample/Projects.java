package sample;

public class Projects {

    private int projectId;
    private String projectTitle;
    private String dateOfDelivery;
    private String projectDescribtion;
    private String client_name;
    private String Type;
    private String Manager;


    //Department department;
    //Manager manager;
    //Client client;

    public Projects(int projectId,
                    String projectTitle,
                    String projectDescribtion,
                    String dateOfDelivery,
                    String Type,
                    String client_name,
                    String Manager



                    ) {
        this.projectId = projectId;
        this.client_name=client_name;
        this.projectTitle = projectTitle;
        this.dateOfDelivery = dateOfDelivery;
        this.projectDescribtion = projectDescribtion;
        this.Type = Type;
        this.Manager=Manager;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public void setDateOfDelivery(String dateOfDelivery) {
        this.dateOfDelivery = dateOfDelivery;
    }

    public void setProjectDescribtion(String projectDescribtion) {
        this.projectDescribtion = projectDescribtion;
    }
    public void setClient_name(String client_name) {
            this.client_name = client_name;
    }
    public void setType(String Type) {
            this.Type = Type;
    }public void setManager(String Manager) {
            this.Manager = Manager;
    }

    public int getProjectId() {
        return projectId;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public String getDateOfDelivery() {
        return dateOfDelivery;
    }

    public String getProjectDescribtion() {
        return projectDescribtion;
    }
    public String getClient_name() {
        return this.client_name;
    }
    public String getType() {
        return this.Type;
    }
    public String getManager() {
        return Manager;
    }
}
