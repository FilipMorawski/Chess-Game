package com.filipmorawski.chess;

public class StartingFiguresLayout {

	private FieldButton button;
	private FigureFactory factory;

	public StartingFiguresLayout(FieldButton fieldButton) {
		this.button = fieldButton;
		this.factory = new FigureFactory();
	}

	public void setLayout() {
		String name = button.getButtonTitle();
		Figure createdFigure;

		// Kings
		if (button.getButtonTitle().contains("e")) {
			if (button.getButtonTitle().contains("8")) {
				createdFigure = factory.createFigure("King", 2, button.getButtonTitle(), button.getVerticalID(),
						button.getHorizontalID());
				button.setFigure(createdFigure);
			} else if (button.getButtonTitle().contains("1")) {
				createdFigure = factory.createFigure("King", 1, button.getButtonTitle(), button.getVerticalID(),
						button.getHorizontalID());
				button.setFigure(createdFigure);
			}
		}
		// Queens
		if (button.getButtonTitle().contains("d")) {
			if (button.getButtonTitle().contains("8")) {
				createdFigure = factory.createFigure("Queen", 2, button.getButtonTitle(), button.getVerticalID(),
						button.getHorizontalID());
				button.setFigure(createdFigure);
			} else if (button.getButtonTitle().contains("1")) {
				createdFigure = factory.createFigure("Queen", 1, button.getButtonTitle(), button.getVerticalID(),
						button.getHorizontalID());
				button.setFigure(createdFigure);
			}
		}
		// Couriers
		if (button.getButtonTitle().contains("f") || button.getButtonTitle().contains("c")) {
			if (button.getButtonTitle().contains("8")) {
				createdFigure = factory.createFigure("Courier", 2, button.getButtonTitle(), button.getVerticalID(),
						button.getHorizontalID());
				button.setFigure(createdFigure);
			} else if (button.getButtonTitle().contains("1")) {
				createdFigure = factory.createFigure("Courier", 1, button.getButtonTitle(), button.getVerticalID(),
						button.getHorizontalID());
				button.setFigure(createdFigure);
			}

		}
		// Jumpers
		if (button.getButtonTitle().contains("g") || button.getButtonTitle().contains("b")) {
			if (button.getButtonTitle().contains("8")) {
				createdFigure = factory.createFigure("Jumper", 2, button.getButtonTitle(), button.getVerticalID(),
						button.getHorizontalID());
				button.setFigure(createdFigure);
			} else if (button.getButtonTitle().contains("1")) {
				createdFigure = factory.createFigure("Jumper", 1, button.getButtonTitle(), button.getVerticalID(),
						button.getHorizontalID());
				button.setFigure(createdFigure);
			}
		}

		// towers
		if (button.getButtonTitle().contains("h") || button.getButtonTitle().contains("a")) {
			if (button.getButtonTitle().contains("8")) {
				createdFigure = factory.createFigure("Tower", 2, button.getButtonTitle(), button.getVerticalID(),
						button.getHorizontalID());
				button.setFigure(createdFigure);
			} else if (button.getButtonTitle().contains("1")) {
				createdFigure = factory.createFigure("Tower", 1, button.getButtonTitle(), button.getVerticalID(),
						button.getHorizontalID());
				button.setFigure(createdFigure);
			}
		}
		// pawns
		if (button.getButtonTitle().contains("2") || button.getButtonTitle().contains("7")) {
			if (button.getButtonTitle().contains("7")) {
				createdFigure = factory.createFigure("Pawn", 2, button.getButtonTitle(), button.getVerticalID(),
						button.getHorizontalID());
				button.setFigure(createdFigure);
			} else {
				createdFigure = factory.createFigure("Pawn", 1, button.getButtonTitle(), button.getVerticalID(),
						button.getHorizontalID());
				button.setFigure(createdFigure);
			}
		}

		Figure figure = button.getFigure();

		if (figure != null) {
			button.setButtonIcon(figure.getIcon());
			button.setIsPiece(true);
			button.setIcon(figure.getIcon());
			button.setDisabledIcon(figure.getIcon());
		}
	}
}
