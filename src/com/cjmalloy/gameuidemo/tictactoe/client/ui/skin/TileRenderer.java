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
 *
 */
public class TileRenderer
{

    private static TileRenderer instance = null;

    protected Renderer gridRenderer = new Renderer()
    {
        private final double ONE_3 = 1.0/3.0;
        private final double TWO_3 = 2.0/3.0;
        @Override
        public void render(Context2d g, double timestamp)
        {
            g.beginPath();
            g.moveTo(0, ONE_3);
            g.lineTo(1, ONE_3);
            g.moveTo(0, TWO_3);
            g.lineTo(1, TWO_3);
            g.moveTo(ONE_3, 0);
            g.lineTo(ONE_3, 1);
            g.moveTo(TWO_3, 0);
            g.lineTo(TWO_3, 1);

            g.setLineWidth(0.01);
            g.setStrokeStyle("#000");
            g.setLineCap(LineCap.SQUARE);
            g.stroke();
        }
    };

    protected Renderer xRenderer = new Renderer()
    {
        @Override
        public void render(Context2d g, double timestamp)
        {
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

    protected Renderer oRenderer = new Renderer()
    {
        @Override
        public void render(Context2d g, double timestamp)
        {
            g.beginPath();
            g.arc(0.5, 0.5, 0.3, 0, 7);

            g.setLineWidth(0.1);
            g.setStrokeStyle("#000");
            g.stroke();
        }
    };

    protected Renderer resetRenderer = new Renderer()
    {
        private final double R = 0.3;
        private final double PI_4 = Math.PI/4;
        private final double SIN_R = Math.sin(PI_4)*R;
        private final double ARROW_LENGTH = 0.2;

        @Override
        public void render(Context2d g, double timestamp)
        {
            g.beginPath();
            g.arc(0.5, 0.5, R, -PI_4*3, PI_4*3);
            g.moveTo(0.5 - SIN_R, 0.5 - SIN_R - ARROW_LENGTH);
            g.lineTo(0.5 - SIN_R, 0.5 - SIN_R);
            g.lineTo(0.5 - SIN_R + ARROW_LENGTH, 0.5 - SIN_R);

            g.setLineWidth(0.1);
            g.setStrokeStyle("#000");
            g.setLineJoin(LineJoin.MITER);
            g.stroke();
        }
    };

    protected TileRenderer() {}

    public Renderer getGridRenderer()
    {
        return gridRenderer;
    }

    public Renderer getORenderer()
    {
        return oRenderer;
    }

    public Renderer getRenderer(Piece p)
    {
        switch (p)
        {
        case X: return xRenderer;
        case O: return oRenderer;
        }
        return null;
    }

    public Renderer getResetRenderer()
    {
        return resetRenderer;
    }

    public Renderer getXRenderer()
    {
        return xRenderer;
    }

    public static TileRenderer get()
    {
        if (instance == null)
        {
            instance = new TileRenderer();
        }
        return instance ;
    }
}
