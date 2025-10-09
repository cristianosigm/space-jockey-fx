package com.cs2tech.old.jshooter.components.graphics;

import com.cs2tech.old.jshooter.components.GameLogger;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * <p>
 * Esta classe implementa um repositario de imagens em buffer disponaveis para o
 * jShooter. Implementa tambam um carregador dinamico (utilizando
 * <b>MediaTracker</b>) para que as imagens estejam corretamente alocadas antes
 * de tentar exibi-las.
 * </p>
 * <p>
 * As imagens sao carregadas conforme configuradas no arquivo
 * <b>images.properties</b>, e sao disponibilizadas numa lista interna, cujo
 * andice a o mesmo namero configurado no arquivo de propriedades.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public class ImageRepository extends Canvas {
    private static final long serialVersionUID = 1418881707620863333L;
    /**
     * Instancia Interna (singleton).
     */
    private static ImageRepository instance = null;
    /**
     * Gerenciador de logs da classe.
     */
    private final GameLogger log = GameLogger.getInstance();
    /**
     * Lista interna de imagens.
     */
    private BufferedImage[] images;
    /**
     * Carregador de imagens.
     */
    private MediaTracker mtrack;

    /**
     * Construtor padrao.
     */
    private ImageRepository() {
        setIgnoreRepaint(true);
        Image[] tempImgs;
        try {
            // loading the images
            final Properties prop = new Properties();
            prop.load(new FileInputStream("cfg/images.properties"));
            final int listSize = Integer.parseInt(prop.getProperty("QTY"));

            String url = "";
            images = new BufferedImage[listSize];
            // the temporary storage for images
            tempImgs = new Image[listSize];

            // reading the images from disk
            for (int i = 0; i < listSize; i++) {
                url = prop.getProperty("IMG" + i);
                tempImgs[i] = getToolkit().getImage(url);
            }

            // buffering all temporary images
            mtrack = new MediaTracker(this);

            try {
                for (int i = 0; i < tempImgs.length; i++) {
                    log.addDebug("Carregando a imagem namero: " + i, this);
                    mtrack.addImage(tempImgs[i], i);
                }
                // waiting to finish the loading
                while (!mtrack.checkAll()) {
                    try {
                        mtrack.waitForAll();
                        Thread.sleep(1000);
                    } catch (final Exception exc) {
                        // nothing to do
                    }
                }
                // all images loaded. Storing the buffered images
                for (int j = 0; j < tempImgs.length; j++) {
                    images[j] = new BufferedImage(tempImgs[j].getWidth(this), tempImgs[j].getHeight(this), BufferedImage.TYPE_INT_ARGB);
                    // Transferring the image to the ImageBuffer
                    images[j].getGraphics()
                        .drawImage(tempImgs[j], 0, 0, this);
                }
                // deleting the temporary image array
                tempImgs = null;
            } catch (final Exception e) {
                log.addError("Falha geral ao tentar carregar a lista de imagens: " + e.getMessage(), e, this);
            }
        } catch (final FileNotFoundException e) {
            log.addError("Arquivo nao encontrado ao tentar carregar a lista de imagens: ", e, this);
        } catch (final IOException e) {
            log.addError("Erro de Entrada e Saada ao tentar carregar a lista de imagens: ", e, this);
        }
    }

    /**
     * @return A instancia atual de ImageRepository, ou uma nova instancia caso
     * a atual esteja nula.
     */
    public static ImageRepository getInstance() {
        if (ImageRepository.instance == null) {
            ImageRepository.instance = new ImageRepository();
        }
        return ImageRepository.instance;
    }

    /**
     * Retorna a imagem solicitada.
     *
     * @param index
     *     andice da imagem solicitada no arquivo de configuraaao
     *     (images.properties).
     *
     * @return Instancia de imagem carregada em buffer.
     */
    public BufferedImage getImage(final int index) {
        return images[index];
    }

    /*
     * (non-Javadoc)
     *
     * @see java.awt.Canvas#paint(java.awt.Graphics)
     */
    @Override
    public void paint(final Graphics gfx) {
    }

    /*
     * (non-Javadoc)
     *
     * @see java.awt.Canvas#update(java.awt.Graphics)
     */
    @Override
    public void update(final Graphics gfx) {
    }

    /*
     * (non-Javadoc)
     *
     * @see java.awt.Component#paintAll(java.awt.Graphics)
     */
    @Override
    public void paintAll(final Graphics gfx) {
    }

    /*
     * (non-Javadoc)
     *
     * @see java.awt.Component#repaint()
     */
    @Override
    public void repaint() {
    }

    /*
     * (non-Javadoc)
     *
     * @see java.awt.Component#print(java.awt.Graphics)
     */
    @Override
    public void print(final Graphics gfx) {
    }

    /*
     * (non-Javadoc)
     *
     * @see java.awt.Component#printAll(java.awt.Graphics)
     */
    @Override
    public void printAll(final Graphics gfx) {
    }
}
