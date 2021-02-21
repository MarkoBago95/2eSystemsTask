package app;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;


@Entity
@Table(name = "subscriptions")
@SecondaryTable(name = "metar", pkJoinColumns = @PrimaryKeyJoinColumn(name = "airport_id"))//work with a second table
public class Subscriptions {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "icao")
	private String icao;

	@Column(name = "active")
	private Integer active;

	@Column(name = "metar_value", table = "metar")
	private String metar_value;

	@Column(name = "icaoM", table = "metar")
	private String icaoM;
	
	@Column(name = "name", table = "metar")
	private String name;

	@Column(name ="temperature", table = "metar")
	private Integer temperature;
	
	@Column(name ="wind", table = "metar")
	private Integer wind;
	
	@Column(name ="visibility", table = "metar")
	private String visibility;
	
	@Column(name ="elevation", table = "metar")
	private Integer elevation;

	public Subscriptions() {
		super();
	}

	public Subscriptions(Integer id, String icao, Integer active) {
		this.id = id;
		this.icao = icao;
		this.active = active;
	}

	
	

	public Integer getTemperature() {
		return temperature;
	}

	public void setTemperature(Integer temperature) {
		this.temperature = temperature;
	}

	public Integer getWind() {
		return wind;
	}

	public void setWind(Integer wind) {
		this.wind = wind;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public Integer getElevation() {
		return elevation;
	}

	public void setElevation(Integer elevation) {
		this.elevation = elevation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcaoM() {
		return icaoM;
	}

	public void setIcaoM(String icaoM) {
		this.icaoM = icaoM;
	}

	public String getMetar_value() {
		return metar_value;
	}

	public void setMetar_value(String metar_value) {
		this.metar_value = metar_value;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIcao() {
		return icao;
	}

	public void setIcao(String icao) {
		this.icao = icao;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

}
