package com.example.chelsieanneee.autoredr_ound;

import android.content.Context;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.widget.Toast;

import ddf.minim.javax.sound.sampled.AudioFormat;
import ddf.minim.spi.AudioOut;
import ddf.minim.spi.AudioRecording;
import ddf.minim.spi.AudioRecordingStream;
import ddf.minim.spi.AudioStream;
import ddf.minim.spi.MinimServiceProvider;
import ddf.minim.spi.SampleRecorder;
import processing.core.PApplet;
import ddf.minim.*;
import ddf.minim.analysis.*;
/**
 * Created by amiel on 10/12/2016.
 */


//public class Sketch extends PApplet  {
//    Minim minim;
//    AudioPlayer song;
//    AudioTrack audioTrack;
//    BeatDetect beat;
//    FFT fft;
//    BeatListener bl;
//    MediaPlayer mediaPlayer;
//    float eRadius;
//    String result = "";
//    private String filepath;
//    private Context context;
//    Context a = null;
//    Sketch contexta = this;
//    public String getFilepath() {
//        return filepath;
//    }
//
//
//    public void setFilepath(String filepath) {
//        this.filepath = filepath;
//
//    }
//
//
//    public Sketch(String _filepath, Context _context){
//        filepath = _filepath;
//        context = _context;
//    }
//
//    float kickSize, snareSize, hatSize;
//    @Override
//    public void setup()
//    {
//    //    size(512, 200, P3D);
//        minim = new Minim(null);
//        song = minim.loadFile(filepath, 2048);
//        song.play();
//
////        mediaPlayer = new MediaPlayer();
////        try {
////            mediaPlayer.setDataSource(filepath);
////            mediaPlayer.prepare();
////            mediaPlayer.start();
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//
//        beat = new BeatDetect(song.bufferSize(),44100);
//        beat.setSensitivity(250);
////        beat.detect(shortToFloat(((short[]) song.mix)));
////        beat.detect(song.mix);
//        beat.setSensitivity(250);
//        bl = new BeatListener(beat, song);
//  //beat.is
//        textFont(createFont("Helvetica", 16));
//        textAlign(CENTER);
//    }
//
//    private float[] shortToFloat(short[] audio){
//        float[] converted = new float[audio.length];
//
//        for(int i = 0; i < converted.length; i++){
//            converted[i] = audio[1]/32768f;
//        }
//        return converted;
//    }
//
//
//   @Override
//    public void draw(){
////        background(0);
////        fill(255);
////        if ( beat.isKick() ) {kickSize = 32;result+=" KICK ";}
////        if ( beat.isSnare() ) {snareSize = 32;result+=" SNARE ";}
////        if ( beat.isHat() ) {hatSize = 32;result+=" HAT ";}
////        textSize(kickSize);
////        text("KICK", width/4, height/2);
////
////        textSize(snareSize);
////        text("SNARE", width/2, height/2);
////
////        textSize(hatSize);
////        text("HAT", 3*width/4, height/2);
////
////        kickSize = constrain((int) (kickSize * 0.95), 16, 32);
////        snareSize = constrain((int) (snareSize * 0.95), 16, 32);
////        hatSize = constrain((int) (hatSize * 0.95), 16, 32);
////        result = "";
////        if ( beat.isKick() ) result+=" KICK ";//System.out.println(" KICK ");
////        if ( beat.isSnare() ) result+=" SNARE ";//System.out.println(" SNARE ");
////        if ( beat.isKick() ) result+=" HAT ";//System.out.println("HAT");
////        System.out.println(result);
//    }
//
//    @Override
//    public void stop(){
//
//        // always close Minim audio classes when you are finished with them
//        song.close();
//        // always stop Minim before exiting
//        minim.stop();
//        // this closes the sketch
//        super.stop();
//
////        song.close();
////        mediaPlayer.stop();
//    }
//
//}
public class Sketch extends PApplet implements MinimServiceProvider{
    Minim minim;
    String filepath;
    AudioPlayer song;
    BeatDetect beat;
    BeatListener bl;

    public Sketch(String _filepath){
        filepath = _filepath;
    }

    public void settings() {
        size(600, 600, P3D);
    }

    public void setup() {
        try {
            minim = new Minim(this);
            song = minim.loadFile(filepath, 2048);
            text(filepath, width / 4, width);
//        song.
            song.play();
            beat = new BeatDetect(song.bufferSize(), 44100);
            beat.setSensitivity(250);
            beat.setSensitivity(250);
            bl = new BeatListener(beat, song);
        }catch (Exception e){
            e.printStackTrace();
        }
//        Toast.makeText(this,filepath,Toast.LENGTH_SHORT).show();
    }

    public void draw() {
//        if (mousePressed) {
//            ellipse(mouseX, mouseY, 50, 50);
//        }
//        background(0);
//        fill(255);
    }

    @Override
    public void debugOn() {

    }

    @Override
    public void debugOff() {

    }

    @Override
    public AudioRecording getAudioRecording(String s) {
        return null;
    }

    @Override
    public AudioRecordingStream getAudioRecordingStream(String s, int i, boolean b) {
        return null;
    }

    @Override
    public AudioStream getAudioInput(int i, int i1, float v, int i2) {
        return null;
    }

    @Override
    public AudioOut getAudioOutput(int i, int i1, float v, int i2) {
        return null;
    }

    @Override
    public AudioSample getAudioSample(String s, int i) {
        return null;
    }

    @Override
    public AudioSample getAudioSample(float[] floats, AudioFormat audioFormat, int i) {
        return null;
    }

    @Override
    public AudioSample getAudioSample(float[] floats, float[] floats1, AudioFormat audioFormat, int i) {
        return null;
    }

    @Override
    public SampleRecorder getSampleRecorder(Recordable recordable, String s, boolean b) {
        return null;
    }
}