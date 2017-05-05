package mil.nga.geopackage.extension.elevation;

import mil.nga.geopackage.tiles.TileBoundingBoxUtils;
import mil.nga.sf.GeometryEnvelope;

/**
 * Elevation request to retrieve elevation values for a point or bounding box
 * 
 * @author osbornb
 * @since 1.2.1
 */
public class ElevationRequest {

	/**
	 * Bounding box
	 */
	private GeometryEnvelope boundingBox;

	/**
	 * Point flag, true when a single point request
	 */
	private boolean point;

	/**
	 * Bounding box projected to the elevation tiles projection
	 */
	private GeometryEnvelope projectedBoundingBox;

	/**
	 * Constructor
	 * 
	 * @param boundingBox
	 *            bounding box
	 */
	public ElevationRequest(GeometryEnvelope boundingBox) {
		this.boundingBox = boundingBox;
	}

	/**
	 * Constructor
	 * 
	 * @param latitude
	 *            latitude coordinate
	 * @param longitude
	 *            longitude coordinate
	 */
	public ElevationRequest(double latitude, double longitude) {
		this(new GeometryEnvelope(longitude, latitude, longitude, latitude));
		point = true;
	}

	/**
	 * Get the bounding box
	 * 
	 * @return bounding box
	 */
	public GeometryEnvelope getBoundingBox() {
		return boundingBox;
	}

	/**
	 * Is the request for a single point
	 * 
	 * @return true if a point request
	 */
	public boolean isPoint() {
		return point;
	}

	/**
	 * Get the projected bounding box
	 * 
	 * @return projected bounding box
	 */
	public GeometryEnvelope getProjectedBoundingBox() {
		return projectedBoundingBox;
	}

	/**
	 * Set the projected bounding box
	 * 
	 * @param projectedBoundingBox
	 *            projected bounding box
	 */
	public void setProjectedBoundingBox(GeometryEnvelope projectedBoundingBox) {
		this.projectedBoundingBox = projectedBoundingBox;
	}

	/**
	 * Get the bounding box overlap between the projected bounding box and the
	 * elevation bounding box
	 * 
	 * @param projectedElevation
	 *            projected elevation
	 * @return overlap bounding box
	 */
	public GeometryEnvelope overlap(GeometryEnvelope projectedElevation) {
		GeometryEnvelope overlap = null;
		if (point) {
			overlap = projectedBoundingBox;
		} else {
			overlap = GeometryEnvelope.overlap(projectedBoundingBox,
					projectedElevation);
		}
		return overlap;
	}

}
