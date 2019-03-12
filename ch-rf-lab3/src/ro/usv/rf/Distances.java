/**
 * 
 */
package ro.usv.rf;

/**
 * @author Student
 *
 */
public class Distances {

	private double euclidian = 0;
	private double Mahalanobis = 0;
	private double Cebisev = 0;
	private double CityBlock = 0;

	public double getEuclidian() {
		return euclidian;
	}
	public void setEuclidian(double euclidian) {
		this.euclidian = euclidian;
	}
	public double getMahalanobis() {
		return Mahalanobis;
	}
	public void setMahalanobis(double mahalanobis) {
		Mahalanobis = mahalanobis;
	}
	public double getCebisev() {
		return Cebisev;
	}
	public void setCebisev(double cebisev) {
		Cebisev = cebisev;
	}
	public double getCityBlock() {
		return CityBlock;
	}
	public void setCityBlock(double cityBlock) {
		CityBlock = cityBlock;
	}
}
