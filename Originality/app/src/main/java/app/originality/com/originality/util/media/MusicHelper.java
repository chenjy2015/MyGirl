package app.originality.com.originality.util.media;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import app.originality.com.originality.bean.MusicSourceVO;
import app.originality.com.originality.util.FileUtils;

/**
 *
 * @Description 音乐播放资源辅助类
 * @author cjy
 * @date 2016/3/18
 * @version V1.0
 */
public class MusicHelper {

    public ArrayList<MusicSourceVO> mMusicSourceData;
    public Gson mGson;

    public MusicHelper(){
        mGson = new Gson();
    }




    /**
     * 获取音乐文件 并保存本地
     *
     */
    public void getMusicSourceFile(MusicSourceVO musicSourceVO) {
        InputStream is = null;
        try {
            URL url = new URL(musicSourceVO.getVoicePath());
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(15 * 1000); //15秒超时
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                is = httpURLConnection.getInputStream();
                byte[] data = readStream(is);
                saveMusicSourceFile(data,  musicSourceVO);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 音乐文件保存到本地
     */
    public void saveMusicSourceFile(byte[] bytes, MusicSourceVO musicSourceVO) {
        File fileDir = new File(FileUtils.getCiwongRootFolder().getPath().toString() + File.separator + "MusicSource"+ File.separator);
        fileDir.mkdirs();
        File file = new File(fileDir.getAbsolutePath() + File.separator + musicSourceVO.getVoiceFileName());
        FileOutputStream fos = null;
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            fos = new FileOutputStream(file);
            fos.write(bytes);
            fos.flush();
            fos.close();
            musicSourceVO.setVoiceLocationPath(file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * 读取流
     *
     * @param inStream
     * @return 字节数组
     * @throws Exception
     */
    public static byte[] readStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        outSteam.close();
        inStream.close();
        return outSteam.toByteArray();
    }


}