public class User {
    private String nom;
    private String prenom;
    private String adrMail;
    private String adrPostale;
    private int latitude;
    private int longitude;


    public User(String pnom, String pPrenom, String pAdrMail, String pAdrPostale) {
        nom = pnom;
        prenom = pPrenom;
        adrMail = pAdrMail;
        adrPostale = pAdrPostale;

        if (!adrMail.endsWith("@univ-tlse3.fr")) {
            ;//adresse non conforme
        }


        //partie JSON

        //latitude =
        //longitude =
    }

}
