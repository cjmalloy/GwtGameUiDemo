package com.cjmalloy.gameuidemo.tictactoe.client.ui.skin;

import com.cjmalloy.gameui.client.core.Renderer;
import com.cjmalloy.gameuidemo.tictactoe.client.model.BoardModel.Piece;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.Context2d.LineCap;
import com.google.gwt.canvas.dom.client.Context2d.LineJoin;

/**
 * All renderers assume a 1x1 coordinate space.
 *
 * @author chris
 */
public class TileRenderer {

  private static TileRenderer instance = null;

  protected Renderer gridRenderer = new Renderer() {
    private static final double ONE_3 = 1.0 / 3.0;
    private static final double TWO_3 = 2.0 / 3.0;

    @Override
    public void render(Context2d g, double timestamp) {
      g.beginPath();
      g.rect(0, 0, 1, 1);
      g.moveTo(0.1, ONE_3);
      g.lineTo(0.9, ONE_3);
      g.moveTo(0.1, TWO_3);
      g.lineTo(0.9, TWO_3);
      g.moveTo(ONE_3, 0.1);
      g.lineTo(ONE_3, 0.9);
      g.moveTo(TWO_3, 0.1);
      g.lineTo(TWO_3, 0.9);

      g.setLineWidth(0.01);
      g.setStrokeStyle("#000");
      g.setLineCap(LineCap.SQUARE);
      g.stroke();
    }
  };

  protected Renderer dropHighlightRenderer = new Renderer() {
    @Override
    public void render(Context2d g, double timestamp) {
      g.beginPath();
      g.rect(0.2, 0.2, 0.6, 0.6);

      g.setLineWidth(0.1);
      g.setStrokeStyle("#BBB");
      g.setLineCap(LineCap.ROUND);
      g.stroke();
    }
  };

  protected Renderer xRenderer = new Renderer() {
    @Override
    public void render(Context2d g, double timestamp) {
      g.beginPath();
      g.moveTo(0.2, 0.2);
      g.lineTo(0.8, 0.8);
      g.moveTo(0.2, 0.8);
      g.lineTo(0.8, 0.2);

      g.setLineWidth(0.1);
      g.setStrokeStyle("#000");
      g.setLineCap(LineCap.ROUND);
      g.stroke();
    }
  };

  protected Renderer oRenderer = new Renderer() {
    @Override
    public void render(Context2d g, double timestamp) {
      g.beginPath();
      g.arc(0.5, 0.5, 0.3, 0, 7);

      g.setLineWidth(0.1);
      g.setStrokeStyle("#000");
      g.stroke();
    }
  };

  protected Renderer resetRenderer = new Renderer() {
    private final double r = 0.3;
    private final double pi4 = Math.PI / 4;
    private final double sinR = Math.sin(pi4) * r;
    private final double arrowLength = 0.2;

    @Override
    public void render(Context2d g, double timestamp) {
      g.beginPath();
      g.arc(0.5, 0.5, r, -pi4 * 3, pi4 * 3);
      g.moveTo(0.5 - sinR, 0.5 - sinR - arrowLength);
      g.lineTo(0.5 - sinR, 0.5 - sinR);
      g.lineTo(0.5 - sinR + arrowLength, 0.5 - sinR);

      g.setLineWidth(0.1);
      g.setStrokeStyle("#000");
      g.setLineJoin(LineJoin.MITER);
      g.stroke();
    }
  };

  protected TileRenderer() {}

  public Renderer getGridRenderer() {
    return gridRenderer;
  }

  public Renderer getRenderer(Piece p) {
    switch (p) {
      case X:
        return xRenderer;
      case O:
        return oRenderer;
      case HIGHLIGHT:
        return dropHighlightRenderer;
    }
    return null;
  }

  public Renderer getResetRenderer() {
    return resetRenderer;
  }

  public static TileRenderer get() {
    if (instance == null) {
      instance = new TileRenderer();
    }
    return instance;
  }
}
