package agut_giralt.androidpractreversi.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import agut_giralt.androidpractreversi.R;
import agut_giralt.androidpractreversi.utils.Game;

/**
 * Created by Nil Agut and Jaume Giralt on 13/06/17.
 *
 */

public class FragmentList extends Fragment{

    private Game[] datos =
            new Game[]{
                    new Game("Persona 1", "Asunto del correo 1", "Texto del correo 1"),
                    new Game("Persona 2", "Asunto del correo 2", "Texto del correo 2"),
                    new Game("Persona 3", "Asunto del correo 3", "Texto del correo 3"),
                    new Game("Persona 4", "Asunto del correo 4", "Texto del correo 4"),
                    new Game("Persona 5", "Asunto del correo 5", "Texto del correo 5")};

    private ListView lstListado;

    private GameListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_listado, container, false);
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        lstListado = (ListView)getView().findViewById(R.id.LstListado);
        lstListado.setAdapter(new GameAdapter(this));
        lstListado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View view, int pos, long id) {
                if (listener != null) {
                    listener.onGameSelected((Game) lstListado.getAdapter().getItem(pos));
                }
            }
        });
    }

    @Override
    public void onAttach(Activity ac) {
        super.onAttach(ac);
        try {
            listener = (GameListener) ac;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(ac.toString() + " must implement GameListener");
        }
    }


    class GameAdapter extends ArrayAdapter<Game> {

        Activity context;

        GameAdapter(FragmentList fragmentList) {
            super(fragmentList.getActivity(),R.layout.listitem_game, datos);
            this.context = fragmentList.getActivity();
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.listitem_game, null);

            TextView lblUser = (TextView)item.findViewById(R.id.DBUsername);
            lblUser.setText(datos[position].getUsername());

            TextView lblTime = (TextView)item.findViewById(R.id.DBTime);
            lblTime.setText(datos[position].getTime());

            TextView lblPosition = (TextView)item.findViewById(R.id.DBPosition);
            lblPosition.setText(datos[position].getPosition());

            return(item);
        }
    }

    public interface GameListener {
        void onGameSelected(Game g);
    }

    public void setGameListener(GameListener listener) {
        this.listener = listener;
    }
}
