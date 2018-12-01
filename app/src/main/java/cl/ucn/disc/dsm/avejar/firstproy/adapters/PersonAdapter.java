package cl.ucn.disc.dsm.avejar.firstproy.adapters;

import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

import cl.ucn.disc.dsm.avejar.firstproy.R;
import cl.ucn.disc.dsm.avejar.firstproy.models.Person;

public final class PersonAdapter extends BaseAdapter{

    /**
     * Lista de personas a cargo de este adapter
     */
    private List<Person> lPersons;

    /**
     * Infla las vistas
     */
    private LayoutInflater inflater;

    /**
     * Constructor del adaptador
     *
     * @param context  Instancia del activity
     * @param persons Lista de personas
     */
    public PersonAdapter(Context context, List<Person> persons) {
        inflater = LayoutInflater.from(context);
        this.lPersons = persons;
    }

    public void Load(List<Person> persons) {
        this.lPersons = persons;
    }

    @Override
    public int getCount() {
        return lPersons.size();
    }

    @Override
    public Person getItem(int position) {
        return lPersons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lPersons.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Obtiene a la persona
        final Person person = getItem(position);

        // Persona ViewHolder
        ViewHolder holder;

        // La vista se infla solo si es NULL, sino es asi se reutiliza
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row_person, parent, false);

            holder = new ViewHolder(convertView);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Obtiene los datos de las personas en los TextViews
        // Si es NULL, no se muestra
        holder.name.setText(person.getName().equals("null") ? "Nameless" : person.getName());
        holder.charge.setText(person.getCharge().equals("null") ? "" : person.getCharge());
        holder.unit.setText(person.getUnit().equals("null") ? "" : person.getUnit());
        holder.email.setText(person.getEmail().equals("null") ? "" : person.getEmail());
        holder.phone.setText(person.getPhone().equals("null") ? "" : person.getPhone());
        holder.office.setText(person.getOffice().equals("null") ? "" : person.getOffice());

        // Devuelve la vista con la info. de la persona
        return convertView;
    }

    /**
     * Clase Interna --> Persona ViewHolder
     */
    private static class ViewHolder {
        TextView name;
        TextView charge;
        TextView unit;
        TextView email;
        TextView phone;
        TextView office;

        public ViewHolder(View view) {
            this.name = view.findViewById(R.id.tv_name);
            this.charge = view.findViewById(R.id.tv_charge);
            this.unit = view.findViewById(R.id.tv_unit);
            this.email = view.findViewById(R.id.tv_email);
            this.phone = view.findViewById(R.id.tv_phone);
            this.office = view.findViewById(R.id.tv_office);
        }
    }
}
