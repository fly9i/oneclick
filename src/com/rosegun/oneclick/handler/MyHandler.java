package com.rosegun.oneclick.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;

class MyHandler extends Handler{  
    @Override  
    public void handleMessage(Message msg) {  
        int what = msg.what;  
        switch(what){  
        case 100://单击按键广播  
            Bundle data = msg.getData();  
            //按键值  
            int keyCode = data.getInt("key_code");  
            //按键时长  
            long eventTime = data.getLong("event_time");  
            //设置超过2000毫秒，就触发长按事件  
            boolean isLongPress = (eventTime>2000);  
              
            switch(keyCode){  
            case KeyEvent.KEYCODE_HEADSETHOOK://播放或暂停  
            case KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE://播放或暂停  
               
                break;  
                  
            //短按=播放下一首音乐，长按=当前音乐快进  
            case KeyEvent.KEYCODE_MEDIA_NEXT:  
                if(isLongPress){  
                }else{  
                }  
                break;  
                  
            //短按=播放上一首音乐，长按=当前音乐快退    
            case KeyEvent.KEYCODE_MEDIA_PREVIOUS:  
                if(isLongPress){  
                }else{  
                }  
                break;  
            }  
              
            break;  
        default://其他消息-则扔回上层处理  
            super.handleMessage(msg);  
        }  
    }  
}  