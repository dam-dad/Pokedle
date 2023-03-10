package teampoke.component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import teampoke.model.Pokemon;

/**
 * 
 * Clase del componente que muestra la información de los Pokémon en la interfaz
 *
 */

public class PokeInfoComponent extends ListCell<Pokemon> implements Initializable {

	// model
    
	private StringProperty nombrePokemon = new SimpleStringProperty();
	private StringProperty tipoPrimPokemon= new SimpleStringProperty();
	private StringProperty tipoSecPokemon = new SimpleStringProperty();
	private StringProperty pesoPokemon = new SimpleStringProperty();
	private StringProperty alturaPokemon = new SimpleStringProperty();
	private IntegerProperty numPokemon = new SimpleIntegerProperty();
	private StringProperty evoPokemon = new SimpleStringProperty();
	private StringProperty preevoPokemon = new SimpleStringProperty();
	private IntegerProperty vidaBasePokemon = new SimpleIntegerProperty();
	
	// view
	
	@FXML
    private HBox PokeInfoView;

	@FXML
    private Label alturaLabel;

    @FXML
    private Label evolucionaLabel;

    @FXML
    private Label vidaBaseLabel;

    @FXML
    private Label numPokedexLabel;

    @FXML
    private Label pesoLabel;

    @FXML
    private ImageView pokemonImage;

    @FXML
    private Label preevolucionLabel;

    @FXML
    private Label tipoPrimarioLabel;

    @FXML
    private Label tipoSecundarioLabel;

	public PokeInfoComponent() {
		super();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PokeInfo.fxml"));
			loader.setController(this);
//			loader.setRoot(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
    

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// bindings
//		pokemonLabel.textProperty().bind(nombrePokemon);
		tipoPrimarioLabel.textProperty().bind(tipoPrimPokemon);
		tipoSecundarioLabel.textProperty().bind(tipoSecPokemon);
		pesoLabel.textProperty().bind(pesoPokemon);
		alturaLabel.textProperty().bind(alturaPokemon);
		numPokedexLabel.textProperty().bind(numPokemon.asString());
		evolucionaLabel.textProperty().bind(evoPokemon);
		preevolucionLabel.textProperty().bind(preevoPokemon);
		vidaBaseLabel.textProperty().bind(vidaBasePokemon.asString());
		
	}

	public final StringProperty nombrePokemonProperty() {
		return this.nombrePokemon;
	}
	

	public final String getNombrePokemon() {
		return this.nombrePokemonProperty().get();
	}
	

	public final void setNombrePokemon(final String nombrePokemon) {
		this.nombrePokemonProperty().set(nombrePokemon);
	}
	

	public final StringProperty tipoPrimPokemonProperty() {
		return this.tipoPrimPokemon;
	}
	

	public final String getTipoPrimPokemon() {
		return this.tipoPrimPokemonProperty().get();
	}
	

	public final void setTipoPrimPokemon(final String tipoPrimPokemon) {
		this.tipoPrimPokemonProperty().set(tipoPrimPokemon);
	}
	

	public final StringProperty tipoSecPokemonProperty() {
		return this.tipoSecPokemon;
	}
	

	public final String getTipoSecPokemon() {
		return this.tipoSecPokemonProperty().get();
	}
	

	public final void setTipoSecPokemon(final String tipoSecPokemon) {
		this.tipoSecPokemonProperty().set(tipoSecPokemon);
	}
	

	public final StringProperty pesoPokemonProperty() {
		return this.pesoPokemon;
	}
	

	public final String getPesoPokemon() {
		return this.pesoPokemonProperty().get();
	}
	

	public final void setPesoPokemon(final String pesoPokemon) {
		this.pesoPokemonProperty().set(pesoPokemon);
	}
	

	public final StringProperty alturaPokemonProperty() {
		return this.alturaPokemon;
	}
	

	public final String getAlturaPokemon() {
		return this.alturaPokemonProperty().get();
	}
	

	public final void setAlturaPokemon(final String alturaPokemon) {
		this.alturaPokemonProperty().set(alturaPokemon);
	}
	

	public final IntegerProperty numPokemonProperty() {
		return this.numPokemon;
	}
	

	public final Integer getNumPokemon() {
		return this.numPokemonProperty().get();
	}
	

	public final void setNumPokemon(final Integer numPokemon) {
		this.numPokemonProperty().set(numPokemon);
	}
	
	public ImageView getPokemonImage() {
		return pokemonImage;
	}

	public HBox getView() {
		return PokeInfoView;
	}
	public Label getAlturaLabel() {
		return alturaLabel;
	}

	public Label getEvolucionaLabel() {
		return evolucionaLabel;
	}

	public Label getNumPokedexLabel() {
		return numPokedexLabel;
	}

	public Label getPesoLabel() {
		return pesoLabel;
	}

	public Label getPreevolucionLabel() {
		return preevolucionLabel;
	}

	public Label getTipoPrimarioLabel() {
		return tipoPrimarioLabel;
	}

	public Label getTipoSecundarioLabel() {
		return tipoSecundarioLabel;
	}

	public final StringProperty evoPokemonProperty() {
		return this.evoPokemon;
	}
	

	public final String getEvoPokemon() {
		return this.evoPokemonProperty().get();
	}
	

	public final void setEvoPokemon(final String evoPokemon) {
		this.evoPokemonProperty().set(evoPokemon);
	}
	

	public final StringProperty preevoPokemonProperty() {
		return this.preevoPokemon;
	}
	

	public final String getPreevoPokemon() {
		return this.preevoPokemonProperty().get();
	}
	

	public final void setPreevoPokemon(final String preevoPokemon) {
		this.preevoPokemonProperty().set(preevoPokemon);
	}

	public final IntegerProperty vidaBasePokemonProperty() {
		return this.vidaBasePokemon;
	}
	

	public final int getVidaBasePokemon() {
		return this.vidaBasePokemonProperty().get();
	}
	

	public final void setVidaBasePokemon(final int vidaBasePokemon) {
		this.vidaBasePokemonProperty().set(vidaBasePokemon);
	}
	
	public Label getVidaBaseLabel() {
		return vidaBaseLabel;
	}

	public void setVidaBaseLabel(Label vidaBaseLabel) {
		this.vidaBaseLabel = vidaBaseLabel;
	}
	
}
