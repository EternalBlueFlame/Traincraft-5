package train.common.core.handlers;

import train.common.api.AbstractTrains;
import train.common.api.EntityRollingStock;
import train.common.api.Locomotive;

import java.util.ArrayList;
import java.util.List;

public class TrainHandler {
	private final List<AbstractTrains> train = new ArrayList<>();
	private int trainPower;

	public TrainHandler() {
	}

	public TrainHandler(EntityRollingStock rolling) {
		addRollingStock(rolling);
		EntityRollingStock.allTrains.add(this);
	}

	public void addRollingStock(AbstractTrains rolling) {
        for (AbstractTrains entityRollingStock : train) {
            if (entityRollingStock.equals(rolling)) {
                return;
            }
        }

		if (rolling instanceof Locomotive) {
			trainPower += rolling.transportMetricHorsePower();
		}

		train.add(rolling);
		(rolling).train = this;
		//System.out.println("added "+rolling);
		if (rolling.frontLink != null) {
			addRollingStock(rolling.frontLink);
		}

		if (rolling.backLink != null) {
			addRollingStock(rolling.backLink);
		}
	}

	public void resetTrain() {
        for (AbstractTrains entityRollingStock : train) {
            if (entityRollingStock != null)
                entityRollingStock.train = null;
        }
		train.clear();
	}

	public List<AbstractTrains> getTrains() {
		return this.train;
	}

	public int getTrainPower() {
		return trainPower;
	}
}