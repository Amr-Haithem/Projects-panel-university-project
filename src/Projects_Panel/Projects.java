package Projects_Panel;

public class Projects {

    private int projectId;
    private String projectTitle;
    private String dateOfDelivery;
    private String projectDescribtion;
    private int client_id;
    private String Type;
    private int Manager;
    private float cost;
    private String PaymentMethod;


    //Department department;
    //Manager manager;
    //Client client;

    public Projects(int projectId,
                    String projectTitle,
                    String projectDescribtion,
                    String dateOfDelivery,
                    String Type,
                    int client_id,
                    int Manager,


                    float cost, String paymentMethod) {
        this.projectId = projectId;
        this.client_id = client_id;
        this.projectTitle = projectTitle;
        this.dateOfDelivery = dateOfDelivery;
        this.projectDescribtion = projectDescribtion;
        this.Type = Type;
        this.Manager = Manager;
        this.cost = cost;
        PaymentMethod = paymentMethod;
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

    public void setClient_name(int client_id) {
        this.client_id = client_id;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public void setManager(int Manager) {
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

    public int getClient_name() {
        return this.client_id;
    }

    public String getType() {
        return this.Type;
    }

    public int getManager() {
        return Manager;
    }

    public float getCost() {
        return cost;
    }

    public String getPaymentMethod() {
        return PaymentMethod;
    }
}
