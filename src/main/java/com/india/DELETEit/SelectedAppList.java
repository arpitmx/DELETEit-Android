package com.india.DELETEit;

import java.util.ArrayList;

public class SelectedAppList {

    private static  ArrayList<Boolean> isChecked  = new ArrayList<Boolean>();

    private static String[] PackagesDB= {"app.buzz.share","com.xiaomi.hm.health","com.lbe.parallel.intl","com.parallel.space.lite","com.CricChat.intl","com.uc.iflow","com.ucturbo","com.uc.browser.en","com.lenovo.anyshare.gps","com.tencent.mobileqq","com.orangenose.trick","cc.forestapp","com.banggood.client","com.hld.anzenbokusucal","qt.door","qlocker.gesture","eztools.calculator.photo.vault","com.androidrocker.voicechanger","com.tap4fun.reignofwar","com.orangenose.speedycar2","instake.repost.instagramphotodownloader.instagram","com.zopik.aji","com.cyberlink.youcammakeup","com.dwiphoto.youcamperfect","com.perfectcorp.ycn","com.cyberlink.youperfect","com.baiwang.instasquare","photo.editor.photoeditor.pro.selfie","photo.beautycamera.selfie.prettycamera","com.uc.vmlite","com.nebula.mamu","com.kwai.bulldog","com.vivashow.share.video.chat","com.kwai.global.video.social.kwaigo","com.prinext.easycollage","com.zhiliaoapp.musically.go","com.newbiz.mvmaster","video.like.lite","com.ss.android.ugc.boomlite","com.ss.android.ugc.trill.go","com.allaboutradio.radiofmchina","com.youku.phone","com.zhiliaoapp.musically","cn.wps.pdf.fillsign","com.funnypuri.client","com.zhiliao.musically.livewallpaper","com.leo.appmaster","com.huawei.hwvplayer","video.like","cn.wps.moffice_eng","cn.wps.moffice_i18n","cn.wps.pdf","com.ngame.allstar.eu","com.quvideo.slideplus","com.funcamerastudio.videoeditor","com.apusapps.launcher","com.apusapps.tools.booster","com.gau.go.launcherex.gowidget.gopowermaster","com.transsion.weathers","com.videoeditorpro.android","","com.picsart.studio","com.picsart.animate","com.nexstreaming.app.kinemasterfree","com.appx.pingguo.launcher","com.transsion.XOSLauncher","com.sinyee.babybus.restaurant","com.sinyee.babybus.shopping","com.pinguo.edit.sdk","vStudio.Android.Camera360","com.camerariocam","camera360.lite.beauty.selfie.camera","com.jb.gokeyboard.langpack.hi","com.ijinshan.kbatterydoctor_en","com.jb.gokeyboard.langpack.ar","com.guardian.security.pri","com.quvideo.vivavideo.lite","com.transsion.phonemaster","sg.bigo.live","sg.bigo.live.lite","cn.wps.moffice_extra","xtub.vid.xvideo.videodownloader","com.kwai.video","com.UCMobile.int","com.cmcm.live","air.com.bitrhymes.bingo","com.ss.android.ugc.boom", "com.commsource.beautyplus", "cn.xender", "com.intsig.camscanner", "com.tencent.ig", "com.hcg.cok.gpl", "com.mobile.legends", "club.fromfactory", "com.zzkko", "com.romwe" ,"com.domobile.applock", "com.uc.vmate", "com.dc.hwsj","com.aceviral.mafiashootout", "com.igg.castleclash", "in.togetu.video" ," com.roposo.android", "com.xprodev.cutcam", "com.snowwhiteapps.downloader", "com.youdao.hindict",  "com.igg.android.lordsmobile", "com.asiainno.uplive", "com.diandian.gog" ,"com.gtarcade.lod", "com.live.videochat.india", "com.dotc.ime.latin.flash", "free.vpn.unblock.proxy.turbovpn", "com.xiaomi.midrop", "com.mi.global.bbs", "com.mi.global.shop" ,"com.newsdog"," com.alibaba.intl.android.apps.poseidon", "com.alibaba.aliexpresshd","com.alibaba.aliexpresshd","com.tencent.mm","com.UCMobile.intl","app.buzz.share.lite","com.theotino.chinadaily","com.innovate.ChinaSocial","com.travelchina.tendays","com.korchix.chineoapp","com.meitu.meipaimv","com.ffgamestudio.fast12306","de.appetites.chinagadgets"};
    private static ArrayList<String> finalPkg = new ArrayList<String>();

    public ArrayList<Boolean> getBools() {
        return isChecked;
    }

    public String[] getPackageDB() {
        return PackagesDB;
    }


    public void addBool(ArrayList<Boolean> checkArr) {
        isChecked = checkArr;
    }


    public ArrayList<String> getFinalPackage() {
        return finalPkg;
    }

    public void setFinalPackage(ArrayList<String> finalPkg) {
         SelectedAppList.finalPkg = finalPkg;
    }



}
