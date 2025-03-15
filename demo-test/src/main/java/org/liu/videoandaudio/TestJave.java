//package org.liu.videoandaudio;
//
//import it.sauronsoftware.jave.*;
//
//import java.io.File;
//
///**
// * 视频及音频的操作
// * 只能在32位机器上使用
// * http://www.sauronsoftware.it/projects/jave/manual.php
// */
//public class TestJave {
//
//    public static void main(String[] args) {
//
//    }
//
//    public static void getInfoFromMultimedia() {
//
//    }
//
//    /**
//     * From a generic AVI to a youtube-like FLV movie, with an embedded MP3 audio stream
//     * @throws EncoderException
//     */
//    public static void transferAVIToFLV() throws EncoderException {
//        File source = new File("source.avi");
//        File target = new File("target.flv");
//        AudioAttributes audio = new AudioAttributes();
//        audio.setCodec("libmp3lame");
//        audio.setBitRate(new Integer(64000));
//        audio.setChannels(new Integer(1));
//        audio.setSamplingRate(new Integer(22050));
//        VideoAttributes video = new VideoAttributes();
//        video.setCodec("flv");
//        video.setBitRate(new Integer(160000));
//        video.setFrameRate(new Integer(15));
//        video.setSize(new VideoSize(400, 300));
//        EncodingAttributes attrs = new EncodingAttributes();
//        attrs.setFormat("flv");
//        attrs.setAudioAttributes(audio);
//        attrs.setVideoAttributes(video);
//        Encoder encoder = new Encoder();
//        encoder.encode(source, target, attrs);
//    }
//
//    /**
//     * Next lines extracts audio informations from an AVI and store them in a plain WAV file
//     * @throws EncoderException
//     */
//    public static void transferAVIToWAV() throws EncoderException {
//        File source = new File("source.avi");
//        File target = new File("target.wav");
//        AudioAttributes audio = new AudioAttributes();
//        audio.setCodec("pcm_s16le");
//        EncodingAttributes attrs = new EncodingAttributes();
//        attrs.setFormat("wav");
//        attrs.setAudioAttributes(audio);
//        Encoder encoder = new Encoder();
//        encoder.encode(source, target, attrs);
//    }
//
//    /**
//     * Next example takes an audio WAV file and generates a 128 kbit/s, stereo, 44100 Hz MP3 file
//     * @throws EncoderException
//     */
//    public static void transferWAVToMP3() throws EncoderException {
//        File source = new File("source.wav");
//        File target = new File("target.mp3");
//        AudioAttributes audio = new AudioAttributes();
//        audio.setCodec("libmp3lame");
//        audio.setBitRate(new Integer(128000));
//        audio.setChannels(new Integer(2));
//        audio.setSamplingRate(new Integer(44100));
//        EncodingAttributes attrs = new EncodingAttributes();
//        attrs.setFormat("mp3");
//        attrs.setAudioAttributes(audio);
//        Encoder encoder = new Encoder();
//        encoder.encode(source, target, attrs);
//    }
//
//    /**
//     * Next one decodes a generic AVI file and creates another one with the same video stream of the source and a re-encoded low quality MP3 audio stream
//     * @throws EncoderException
//     */
//    public static void transferAVIToMP3() throws EncoderException {
//        File source = new File("source.avi");
//        File target = new File("target.avi");
//        AudioAttributes audio = new AudioAttributes();
//        audio.setCodec("libmp3lame");
//        audio.setBitRate(new Integer(56000));
//        audio.setChannels(new Integer(1));
//        audio.setSamplingRate(new Integer(22050));
//        VideoAttributes video = new VideoAttributes();
//        video.setCodec(VideoAttributes.DIRECT_STREAM_COPY);
//        EncodingAttributes attrs = new EncodingAttributes();
//        attrs.setFormat("avi");
//        attrs.setAudioAttributes(audio);
//        attrs.setVideoAttributes(video);
//        Encoder encoder = new Encoder();
//        encoder.encode(source, target, attrs);
//    }
//
//    /**
//     * Next one generates an AVI with MPEG 4/DivX video and OGG Vorbis audio
//     * @throws EncoderException
//     */
//    public static void transferAVI() throws EncoderException {
//        File source = new File("source.avi");
//        File target = new File("target.avi");
//        AudioAttributes audio = new AudioAttributes();
//        audio.setCodec("libvorbis");
//        VideoAttributes video = new VideoAttributes();
//        video.setCodec("mpeg4");
//        video.setTag("DIVX");
//        video.setBitRate(new Integer(160000));
//        video.setFrameRate(new Integer(30));
//        EncodingAttributes attrs = new EncodingAttributes();
//        attrs.setFormat("mpegvideo");
//        attrs.setAudioAttributes(audio);
//        attrs.setVideoAttributes(video);
//        Encoder encoder = new Encoder();
//        encoder.encode(source, target, attrs);
//    }
//
//    /**
//     * A smartphone suitable video
//     * @throws EncoderException
//     */
//    public static void transferAVITo3GP() throws EncoderException {
//        File source = new File("source.avi");
//        File target = new File("target.3gp");
//        AudioAttributes audio = new AudioAttributes();
//        audio.setCodec("libfaac");
//        audio.setBitRate(new Integer(128000));
//        audio.setSamplingRate(new Integer(44100));
//        audio.setChannels(new Integer(2));
//        VideoAttributes video = new VideoAttributes();
//        video.setCodec("mpeg4");
//        video.setBitRate(new Integer(160000));
//        video.setFrameRate(new Integer(15));
//        video.setSize(new VideoSize(176, 144));
//        EncodingAttributes attrs = new EncodingAttributes();
//        attrs.setFormat("3gp");
//        attrs.setAudioAttributes(audio);
//        attrs.setVideoAttributes(video);
//        Encoder encoder = new Encoder();
//        encoder.encode(source, target, attrs);
//    }
//
//}
