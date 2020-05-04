package sample;
import Model.Model;
import Model.Points;
import com.sun.imageio.plugins.png.PNGImageWriter;
import com.sun.imageio.plugins.png.PNGImageWriterSpi;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
public class Controller implements Initializable {
    public Button delete;
    public Button NewLine;
    public Canvas canvas;
    public ColorPicker colorpik;
    public  String flag;
    public Slider sliders;

    Model model;
    private GraphicsContext gr;

    Image Image;
    double X, Y, W = 100.0, H = 100.0;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model=new Model();
        gr = canvas.getGraphicsContext2D();
        Slider1();
    }

    public void Slider1() {
        sliders.setMin(1);
        sliders.setMax(10);
        sliders.setValue(1);
        colorpik.setValue(Color.RED);
        flag =NewLine.getId();
    }
    public void update(Model model) {
        gr.clearRect(0, 0, 1000, 1000);

        for (int i = 0; i < model.getPointCount(); i++) {
            gr.setFill(model.getPoint(i).getColor());
            gr.fillOval(model.getPoint(i).getX(),model.getPoint(i).getY(),model.getPoint(i).getPoint2() ,model.getPoint(i).getPoint1());
        }
    }

    public void clik_canvas(MouseEvent mouseEvent) { }


    /*
    Image test = new Image(getClass().getResourceAsStream("save.png"));
    public Button Save = new Button("save", new ImageView(save));
    */


    public void Save(ActionEvent actionEvent) throws IOException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Сохранение файла....");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Изображение", "*.png"),
                new FileChooser.ExtensionFilter("Изображение", "*.bmp"));
        File file = fileChooser.showSaveDialog(canvas.getScene().getWindow());
        WritableImage wImage = new WritableImage((int)canvas.getWidth(), (int)canvas.getHeight());
        PixelWriter pw = wImage.getPixelWriter();
        for (int y = 0; y < (int)canvas.getHeight(); y++) {
            for (int x = 0; x < (int)canvas.getWidth(); x++){
                int index = model.serchPoint(x,y);
                if (index < 0) {
                    pw.setColor(x, y, Color.TRANSPARENT);
                }
                else{
                    pw.setColor(x,y,model.getPoint(index).getColor());
                }
            }
        }
        BufferedImage image = SwingFXUtils.fromFXImage(wImage, null);
        if (file != null) {
            ImageIO.write(image,"png", new FileOutputStream(file));
            System.out.println(" "+file);
        }
    }


/*
    Image test = new Image(getClass().getResourceAsStream("Load.png"));
    public Button Load = new Button("Load", new ImageView(Load));
    */



    public void Load(ActionEvent actionEvent) throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выбрать ...");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Картинка", "*.png"),
                new FileChooser.ExtensionFilter("Картинка", "*.bmp"));
        File loadImageFile = fileChooser.showOpenDialog(canvas.getScene().getWindow());
        if (loadImageFile != null) {
            initDraw(gr,loadImageFile);
            update(model);
        }
    }
    private void initDraw(GraphicsContext gc,File file){
        String str=file.getPath();
        double canvasWidth = gc.getCanvas().getWidth();
        double canvasHeight = gc.getCanvas().getHeight();

        Image = new Image(file.toURI().toString());
        X = canvasWidth/2;
        Y = canvasHeight/2 ;
        gc.drawImage(Image, X, Y, W, H);

        PixelReader pixelReader = Image.getPixelReader();
        double y1=canvas.getHeight()/Image.getHeight();
        double x1=canvas.getWidth()/Image.getWidth();
        for (int y = 0; y < Image.getHeight(); y++) {
            for (int x = 0; x < Image.getWidth(); x++) {
              Color color = pixelReader.getColor(x, y);
                Points point =new Points(x,y);
                point.setColor(color);
                point.setSizePoint(x1,y1);
                model.addPoint(point);
            }
        }
    }



    public void clean(ActionEvent actionEvent) {
        gr.clearRect(0,0,canvas.getHeight(),canvas.getWidth());
        model.deleteArray();

    }

    public void print(MouseEvent mouseEvent) {
        Points points = new Points((int) mouseEvent.getX(), (int) mouseEvent.getY());
        if (flag == NewLine.getId()) {
            points.setColor(colorpik.getValue());
            points.setSizePoint(sliders.getValue(), sliders.getValue());
            model.addPoint(points);

        } else {
            model.remuvePoint(points);
        }
        update(model);
    }

    public void m_lastick_but(ActionEvent actionEvent) {
        flag=NewLine.getId();
    }

    public void m_line(ActionEvent actionEvent) {
        flag=delete.getId();
    }
}