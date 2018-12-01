package cl.ucn.disc.dsm.avejar.firstproy.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

import lombok.Builder;
import lombok.Getter;

@Builder
public final class Person {

    // Constructor
    public Person(int id, String name, String charge, String unit, String email, String phone, String office) {
        this.id = id;
        this.name = name;
        this.charge = charge;
        this.unit = unit;
        this.email = email;
        this.phone = phone;
        this.office = office;
    }

    /**
     * JSON Constructor
     *
     * @param jsObj
     */
    public Person(JSONObject jsObj) {
        try {
            this.id = jsObj.getInt("id");
            this.name = jsObj.getString("nombre");
            this.charge = jsObj.getString("cargo");
            this.unit = jsObj.getString("unidad");
            this.email = jsObj.getString("email");
            this.phone = jsObj.getString("telefono");
            this.office = jsObj.getString("oficina");

        } catch (JSONException je) {
            je.printStackTrace();
        }
    }

    /**
     * Dado un array de json, crea una lista de personas
     *
     * @param array
     * @return
     */
    public static ArrayList<Person> jsFile(JSONArray array) {

        ArrayList<Person> persons = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            try {
                persons.add(new Person(array.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return persons;
    }

    /**
     * ID Person
     */
    @Getter
    private int id;

    /**
     * Nombre Person
     */
    @Getter
    private String name;

    /**
     * Tipo de Cargo Persona(Funcionario/Academico)
     */
    @Getter
    private String charge;

    /**
     * Unidad Person
     */
    @Getter
    private String unit;

    /**
     * Email Person.
     */
    @Getter
    private String email;

    /**
     * Telefono Person.
     */
    @Getter
    private String phone;

    /**
     * Oficina Person.
     */
    @Getter
    private String office;

}
