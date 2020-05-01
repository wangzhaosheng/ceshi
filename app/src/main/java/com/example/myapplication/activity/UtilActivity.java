package com.example.myapplication.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.provider.BaseColumns;
import android.provider.CallLog;
import android.provider.Telephony;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;

import com.example.myapplication.FileUtils;
import com.example.myapplication.R;
import com.example.myapplication.SimCardSubscriber;
import com.example.myapplication.wifi.WifiHotUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UtilActivity extends AppCompatActivity {
    /**
     * 这些是slotid可能的action
     */
    private static final String[] dualSimTypes = {
            "subscription",
            "Subscription",
            "com.android.phone.extra.slot",
            "phone",
            "com.android.phone.DialingMode",
            "simId",
            "simnum",
            "phone_type",
            "simSlot"
    };
    private static final String TAG = "UtilActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_util);
    }

    public void click11(View view) {

        String json = "[\"X6GNU17B02111137\",\"X6GNU17B06100516\",\"X6GNU17B06100835\",\"X6GNU17B06100046\",\"X6GNU17B02112669\",\"X6GNU17B06100739\",\"X6GNU17B02109236\",\"X6GNU17B06102216\",\"X6GNU17B02111863\",\"X6GNU18B06102463\",\"X6GNU17B06101136\",\"X6GNU17B02108699\",\"X6GNU17B06102242\",\"X6GNU17B06101163\",\"X6GNU17B02109934\",\"WXM6R16912002173\",\"WXM6R16902000899\",\"WXM6R16924000196\",\"WXM6R16912001257\",\"WXM6R16912001315\",\"WXM6R16912001295\",\"WXM6R16924000220\",\"WXM6R16924000262\",\"WXM6R16826001572\",\"WXM6R16924000014\",\"HGEQU2Y8\",\"HGEQU2MY\",\"HGEQU5XE\",\"HGEQU2G3\",\"HGEQU9FQ\",\"HGEQU9LP\",\"HGEQU24P\",\"HGEQUCDJ\",\"HGEQU9UU\",\"HGEQU35P\",\"HGEQUC69\",\"HGEQU215\",\"HGEQU8MW\",\"HGEQU61U\",\"HGEQUB9A\",\"HGEQUAJH\",\"HGEQU2GB\",\"HGEQU4TW\",\"X6GNU17B06102808\",\"X6GNU17B06102995\",\"X6GNU17B06101542\",\"X6GNU17B06100173\",\"X6GNU17B06100108\",\"X6GNU17B06102384\",\"X6GNU17B02108773\",\"X6GNU17B02109234\",\"X6GNU17B02110865\",\"X6GNU17B06100265\",\"X6GNU17B06102338\",\"X6GNU17B06100696\",\"X6GNU17B02107813\",\"X6GNU17B06101933\",\"X6GNU17B06103140\",\"X6GNU17B06101531\",\"X6GNU17B02110785\",\"X6GNU17B02107248\",\"X6GNU17B06103154\",\"X6GNU17B02112921\",\"X6GNU17B06103088\",\"X6GNU17B06102018\",\"X6GNU17B06102714\",\"X6GNU17B06102153\",\"X6GNU17B02112135\",\"X6GNU17B06101033\",\"X6GNU17B06103030\",\"X6GNU17B06100537\",\"X6GNU17B02106985\",\"X6GNU17B06102417\",\"X6GNU17B06103034\",\"X6GNU17B02113392\",\"X6GNU17B06100440\",\"WEY6R16A15001921\",\"WEY6R16A15002091\",\"WEY6R16A15002208\",\"X6GNU17B06103060\",\"X6GNU17B06102995\",\"DVLNU19312101702\",\"WXM6R16B18010432\",\"X6GNU17B06103088\",\"HGEQU60V\",\"X6GNU17B06101034\",\"X6GNU17B06102795\",\"HGEQUCFM\",\"X6GNU17B02107242\",\"X6GNU17B06100036\",\"X6GNU17B06102993\",\"HGEQUARN\",\"WEY6R16B25002583\",\"X6GNU17B02108493\",\"X6GNU17B06100066\",\"X6GNU17B06102556\",\"X6GNU17B06102773\",\"X6GNU17B06100538\",\"WXM6R16C13000757\",\"WXM6R16C13002379\",\"HGEQU7KV\",\"WXM6R16507002945\",\"HGEQUVXM\",\"X6GNU17B06100265\",\"WXM6R16C09004098\",\"X6GNU17B06100094\",\"X6GNU17B06100857\",\"WEY6R16B23002872\",\"WXM6R16C13001845\",\"DVLNU19312101809\",\"DVLNU19312103277\",\"HGEQU9F1\",\"X6GNU17B02109234\",\"WXM6R16908000872\",\"HGEQU6GB\",\"X6GNU17B06103030\",\"X6GNU17B06103162\",\"HGES04YD\",\"WXM6R16C13000541\",\"X6GNU17B06103130\",\"WEY6R16A15001916\",\"47Q6R16B10001633\",\"WXM6R16C13000714\",\"X6GNU17B06102400\",\"WXM6R16924000312\",\"WXM6R16B18009868\",\"X6GNU17B06102362\",\"WXM6R16C13002469\",\"WEY6R16A15002096\",\"DVLNU19312101722\",\"X6GNU17B06102518\",\"HGES04TL\",\"X6GNU17B06101033\",\"X6GNU17B06103140\",\"R22FC017FMD\",\"DVLNU19312104559\",\"X6GNU17B06102960\",\"WEY6R16A15002375\",\"X6GNU17B02105186\",\"DVLNU19312101764\",\"X6GNU17B02112848\",\"47Q6R16B16000751\",\"WXM6R16507002989\",\"DVLNU19312100247\",\"X6GNU17B02112608\",\"X6GNU17B06101780\",\"WEY6R16B23002893\",\"WEY6R16B23002764\",\"X6GNU18412106591\",\"WXM6R16C09004385\",\"X6GNU17B02105980\",\"X6GNU17B06102714\",\"WXM6R16B18009863\",\"47Q6R16B16000984\",\"X6GNU17B06102322\",\"WEY6R16B25000666\",\"DVLNU19312103937\",\"47Q6R16B10001553\",\"DVLNU19312100313\",\"HGEQUAP9\",\"X6GNU17B02103911\",\"X6GNU17B06101795\",\"X6GNU17B06101491\",\"X6GNU17B06100874\",\"X6GNU17B06102537\",\"WXM6R16924000331\",\"DVLNU19312103335\",\"X6GNU17B02110986\",\"X6GNU17B02111381\",\"X6GNU17B06101038\",\"WEY6R16A15000782\",\"WXM6R16C13001774\",\"WXM6R16507002938\",\"WXM6R16507002937\",\"DVLNU19312103831\",\"HGEQUCEE\",\"X6GNU17B06100498\",\"X6GNU17B06100803\",\"WXM6R16C13002542\",\"WEY6R16B25002632\",\"X6GNU17B06101386\",\"X6GNU17B06102338\",\"R22FC0184FP\",\"X6GNU17B06102891\",\"X6GNU17B02103846\",\"X6GNU17B06100313\",\"X6GNU17B06101163\",\"X6GNU17B02106730\",\"X6GNU17B06101761\",\"WXM6R16C13002589\",\"47Q6R16B16000771\",\"WXM6R16C13002427\",\"X6GNU17B02108327\",\"WXM6R16C13001636\",\"X6GNU17B02112583\",\"X6GNU17B06103197\",\"HGEQU6FW\",\"WXM6R16C13003089\",\"X6GNU17B06100539\",\"WXM6R16C13001822\",\"X6GNU17B02109280\",\"WXM6R16C13002502\",\"RV2G4013CWJ\",\"HGEQU7PY\",\"DVLNU19312103961\",\"X6GNU17B02112298\",\"X6GNU17B02108865\",\"WXM6R16A22000600\",\"WEY6R16A15002433\",\"RV2G4014KJE\",\"HGEQUAG6\",\"X6GNU17B06102126\",\"X6GNU17B06103154\",\"DVLNU19312101743\",\"HGEQU9V0\",\"HGES049P\",\"WEY6R16B23002770\",\"WXM6R16C09003442\",\"HGEQU4RQ\",\"WXM6R16507002991\",\"X6GNU17B06101103\",\"X6GNU17B02110728\",\"WEY6R16A15001768\",\"WXM6R16C09004151\",\"WXM6R16C13000783\",\"HGEQU90B\",\"HGEQU6AN\",\"WXM6R16A22000889\",\"X6GNU17B06101933\",\"X6GNU17B02105792\",\"HGEQU2Q2\",\"WXM6R16C13002463\",\"X6GNU18412106453\",\"WEY6R16B25002652\",\"X6GNU17B06101021\",\"HGEQU6TW\",\"X6GNU17B06102242\",\"WXM6R16B18009928\",\"47Q6R16B12000355\",\"X6GNU18319106989\",\"WEY6R16B23003968\",\"WXM6R16507002933\",\"WXM6R16C13002536\",\"X6GNU17B02108870\",\"WEY6R16B23002854\",\"X6GNU17B06102264\",\"X6GNU17B06102138\",\"X6GNU18412106425\",\"X6GNU17B06101330\",\"WXM6R16C09003315\",\"X6GNU17B06102384\",\"DVLNU19312100422\",\"X6GNU17B02110854\",\"X6GNU17B06102216\",\"WXM6R16507002932\",\"X6GNU17B06102764\",\"WXM6R16C13002481\",\"WXM6R16C13002576\",\"WXM6R16507002925\",\"X6GNU17B06102303\",\"X6GNU17B02104937\",\"WXM6R16C13002470\",\"X6GNU18412105819\",\"WXM6R16C13001679\",\"WXM6R16B18009795\",\"DVLNU19312103900\",\"WXM6R17104001111\",\"HGES030H\",\"X6GNU17B02111882\",\"WXM6R16C09004455\",\"WXM6R16B18009832\",\"HGEQU8N7\",\"WXM6R16A22000647\",\"X6GNU17B02109058\",\"X6GNU17B06100537\",\"WEY6R16B23002772\",\"WXM6R16A22000631\",\"X6GNU17B06101643\",\"X6GNU17B06101136\",\"X6GNU17B06103046\",\"WXM6R16A22000802\",\"X6GNU17B02106985\",\"WXM6R16C09004389\",\"X6GNU17B02108758\",\"X6GNU17B06102092\",\"X6GNU17B02108385\",\"X6GNU17B06102914\",\"WXM6R16C13001602\",\"WXM6R16C13000534\",\"X6GNU17B02111029\",\"DVLNU19312101820\",\"WXM6R16B18009735\",\"X6GNU17B06100085\",\"X6GNU17B06101058\",\"HGEQU8EC\",\"47Q6R16B12001160\",\"X6GNU17B06102476\",\"WXM6R16C13002596\",\"X6GNU17B02112135\",\"WEY6R16A15002099\",\"X6GNU17B06103078\",\"WEY6R16A15001354\",\"X6GNU17B06100649\",\"X6GNU17B06102463\",\"HGEQUW08\",\"WEY6R16B23002883\",\"HGEQU35C\",\"X6GNU17B06102140\",\"HGEQU9X2\",\"DVLNU19312101742\",\"HGEQUWKW\",\"HGEQUVS7\",\"HGEQUW3V\",\"X6GNU17B06101884\",\"DVLNU19312100128\",\"X6GNU17B06102217\",\"WXM6R16507002940\",\"X6GNU17B02113392\",\"X6GNU17B06101770\",\"WXM6R16C13002516\",\"WXM6R16B18009746\",\"47Q6R16B16000687\",\"WXM6R16C09004496\",\"WXM6R16C13002528\",\"WXM6R16C13000474\",\"X6GNU17B06101689\",\"X6GNU17B02112270\",\"DVLNU19312101870\",\"DVLNU19312100105\",\"WXM6R16C13002534\",\"X6GNU18319104366\",\"X6GNU17B06102555\",\"WXM6R16C13003150\",\"X6GNU17B06101340\",\"WXM6R16C13002378\",\"WXM6R16C13001600\",\"X6GNU17B06100696\",\"WXM6R16C09003462\",\"X6GNU17B02110618\",\"WXM6R16C09003227\",\"WXM6R16C13002462\",\"X6GNU17B02109236\",\"DVLNU19305102335\",\"X6GNU17B06100913\",\"WXM6R16924000356\",\"X6GNU17B06100481\",\"X6GNU17B06102153\",\"DVLNU19312103934\",\"X6GNU17B06100064\",\"X6GNU17B06102768\",\"HGEQU9UA\",\"X6GNU17B06102808\",\"HGEQU29H\",\"X6GNU17B06103033\",\"X6GNU17B06101354\",\"WEY6R16B23002774\",\"HGES04UQ\",\"RV2G401464K\",\"HGEQU21G\",\"X6GNU17B02106952\",\"WXM6R16C13002720\",\"WXM6R16C13001867\",\"X6GNU17B06100237\",\"WXM6R16C13000702\",\"X6GNU18412106141\",\"WEY6R16A15002470\",\"DVLNU19312104669\",\"WXM6R16C13000774\",\"X6GNU17B06103128\",\"HGES049S\",\"X6GNU17B02106844\",\"X6GNU18412106579\",\"X6GNU17B06100599\",\"WXM6R16C09004388\",\"47Q6R16B16000981\",\"X6GNU17B02112858\",\"WXM6R16C13000667\",\"WXM6R16C13002604\",\"HGEQU7VR\",\"WEY6R16B23002922\",\"X6GNU17B06100440\",\"X6GNU17B02111507\",\"WXM6R16C13000773\",\"WXM6R16924000330\",\"WXM6R16C13002390\",\"WEY6R16B23005373\",\"X6GNU17B06100704\",\"47Q6R16202002200\",\"47Q6R16B12001159\",\"WXM6R16C13000775\",\"WXM6R16C13002484\",\"X6GNU17B02107953\",\"WXM6R16B18009914\",\"X6GNU17B02112669\",\"HGEQU7RZ\",\"WXM6R16A22000651\",\"X6GNU17B06100990\",\"HGEQU4TQ\",\"X6GNU17B06101597\",\"X6GNU17927117827\",\"X6GNU17B06101332\",\"X6GNU17B06101804\",\"X6GNU17B06101402\",\"X6GNU17927117805\",\"WXM6R16B18009925\",\"WXM6R16A22000654\",\"X6GNU17B02112560\",\"X6GNU17B06101889\",\"WXM6R16C13000312\",\"X6GNU17B06101736\",\"HGEQU4RF\",\"WXM6R16924000335\",\"WXM6R16B18009920\",\"HGES04DE\",\"WXM6R16C13003147\",\"WXM6R17104001116\",\"X6GNU17B06102120\",\"X6GNU17B06100366\",\"X6GNU17B02103763\",\"R22FC017R6W\",\"DVLNU19312103941\",\"47Q6R16B16000671\",\"HGEQUBYR\",\"WXMDU17112000161\",\"HGEQU9U9\",\"WXM6R16C13002524\",\"X6GNU17B06102031\",\"X6GNU17B06101350\",\"X6GNU17B02112921\",\"WXM6R16C13003080\",\"WEY6R16B23002886\",\"WXM6R16C09004384\",\"DVLNU19312103902\",\"X6GNU17B02111684\",\"WXM6R16C09004503\",\"RV2G401389R\",\"X6GNU17B06100225\",\"X6GNU17B06101564\",\"HGEQU80D\",\"WXM6R16C13001524\",\"WXM6R16C13002429\",\"DVLNU19312103318\",\"WXM6R16C13002631\",\"X6GNU17B06101619\",\"X6GNU17B02110154\",\"X6GNU18412106783\",\"WXM6R16C13002486\",\"47Q6R16B16000774\",\"WXM6R16A22000649\",\"X6GNU17B02110359\",\"WXM6R16C13000475\",\"WXM6R16C09004219\",\"DVLNU19312103849\",\"WEY6R16B23002616\",\"X6GNU17B02106350\",\"HGEQU62Z\",\"X6GNU17B06101385\",\"X6GNU17B06101451\",\"X6GNU17B06100739\",\"X6GNU17B06100087\",\"WXM6R16C09004270\",\"X6GNU17B06102008\",\"WXM6R16B18009737\",\"47Q6R16B12001155\",\"X6GNU17B06102003\",\"HGEQU924\",\"X6GNU17B06100867\",\"WXM6R16C09004497\",\"47Q6R16B12001161\",\"WXM6R17104001244\",\"X6GNU17B06102261\",\"DVLNU19312109425\",\"DVLNU19312102453\",\"X6GNU17B06101561\",\"X6GNU18412105650\",\"X6GNU17B06100391\",\"WXM6R16507001461\",\"WXM6R16C13001607\",\"X6GNU17B02104719\",\"X6GNU17B06101249\",\"WXM6R16A22000952\",\"WXM6R16C13002498\",\"WXM6R16C13003138\",\"WXM6R16A22000915\",\"X6GNU17B06102388\",\"X6GNU17B06102920\",\"X6GNU17B06101501\",\"X6GNU17B06101518\",\"DVLNU19312100107\",\"WXM6R16B18009860\",\"X6GNU17B06102657\",\"X6GNU17927118248\",\"X6GNU17B06101567\",\"HGEQUVPX\",\"WEY6R16A15000774\",\"X6GNU17B06102650\",\"X6GNU17B02104774\",\"X6GNU17B02109389\",\"X6GNU17B06101454\",\"X6GNU17B06101455\",\"X6GNU17B06100569\",\"X6GNU17B06102560\",\"X6GNU17B06102799\",\"X6GNU17B06100790\",\"X6GNU17B02107559\",\"HGEQUWDS\",\"DVLNU19312103921\",\"X6GNU18319104439\",\"DVLNU19312104381\",\"X6GNU17B06101168\",\"WEY6R16B23002891\",\"WXM6R16C13001633\",\"RV2G40143BP\",\"X6GNU17B06101490\",\"X6GNU17B02105516\",\"X6GNU17B02108889\",\"RV2G40146QD\",\"WXM6R16C09004490\",\"X6GNU17B02108113\",\"X6GNU17B06100132\",\"X6GNU17B02110672\",\"X6GNU17B06100802\",\"WXM6R16B18009827\",\"WXM6R16C09004504\",\"WXM6R16A22000607\",\"X6GNU17B02106826\",\"47Q6R16B16000690\",\"X6GNU17B06102483\",\"X6GNU17B06101153\",\"X6GNU17B06102638\",\"X6GNU18319104233\",\"X6GNU17B06103050\",\"WXM6R16C13003151\",\"X6GNU17B06103109\",\"HGEQUBUH\",\"X6GNU17B06102016\",\"X6GNU18412105716\",\"WXM6R16C09004382\",\"47Q6R16928001439\",\"WXM6R16C13000444\",\"X6GNU17B06101392\",\"WXM6R16C13000500\",\"HGEQU37M\",\"X6GNU17B02108773\",\"WXM6R16C13002518\",\"X6GNU17B06101542\",\"HGEQUAK2\",\"X6GNU17B02106710\",\"WXM6R16B18009743\",\"X6GNU17B02111295\",\"X6GNU17B06100966\",\"WXM6R16C09004381\",\"X6GNU17B06101588\",\"WXM6R16C13000710\",\"X6GNU17B06101653\",\"X6GNU18412105575\",\"DVLNU19312103298\",\"X6GNU17B06102301\",\"X6GNU17B02108637\",\"HGEQUW2H\",\"47Q6R16928001442\",\"WXM6R16C13001674\",\"HGEQU6TC\",\"X6GNU17B06102534\",\"DVLNU19312103308\",\"X6GNU17B02109250\",\"HGEQU8JN\",\"X6GNU17B06100160\",\"WXM6R16C13000763\",\"WXM6R16C09004495\",\"WEY6R16B25000629\",\"HGEQU25A\",\"X6GNU17B06100835\",\"X6GNU17B06101507\",\"X6GNU17B06101202\",\"WXM6R16908000875\",\"X6GNU17B02109735\",\"WXM6R16C13003134\",\"WXM6R16B18010606\",\"X6GNU17B06100673\",\"DVLNU19312101872\",\"X6GNU17B02109485\",\"DVLNU19312100444\",\"DVLNU19312103238\",\"WXM6R16C09004169\",\"X6GNU17B02107601\",\"DVLNU19312103893\",\"WXM6R16B18009927\",\"HGEQUVKP\",\"WEY6R16B23002624\",\"X6GNU17B06100173\",\"X6GNU17B06102230\",\"X6GNU17B02109563\",\"X6GNU17B06102916\",\"HGEQU9XD\",\"WXM6R16B18009867\",\"X6GNU17B06100982\",\"47Q6R16202002141\",\"HGEQU7ZT\",\"RV2G401413M\",\"WXM6R16C13002588\",\"X6GNU17B02107061\",\"WEY6R16B23002773\",\"X6GNU17B06100506\",\"HGEQU25U\",\"X6GNU17B06103051\",\"X6GNU17B06103074\",\"WXM6R16A22000702\",\"X6GNU17B02106401\",\"WEY6R16B23002632\",\"X6GNU17B02111137\",\"HGEQU9KH\",\"X6GNU17B06102975\",\"X6GNU17B06101774\",\"RV2G4013VFX\",\"X6GNU17B06101830\",\"DVLNU19312100099\",\"WXM6R16C09004498\",\"X6GNU17B02106230\",\"X6GNU17B02109071\",\"HGEQUAMP\",\"WXM6R16C13000743\",\"WXM6R16C09004371\",\"X6GNU17B06101363\",\"X6GNU17B06102688\",\"X6GNU17B02104446\",\"WEY6R16B25002597\",\"X6GNU17B02111631\",\"WXM6R16C09004380\",\"X6GNU18412107580\",\"DVLNU19312104760\",\"R22FC017GHW\",\"X6GNU18412107181\",\"WXM6R16C13002554\",\"X6GNU17B02106893\",\"WEY6R16B23002763\",\"X6GNU17B06102821\",\"DVLNU19312101687\",\"X6GNU17B06100353\",\"X6GNU17B06100371\",\"X6GNU17B06101366\",\"WXM6R16C13002569\",\"X6GNU17B06102417\",\"R22FC017FFR\",\"DVLNU19312100150\",\"X6GNU17B02111979\",\"HGEQU7JG\",\"X6GNU17B02108557\",\"HGEQU8EN\",\"WEY6R16B23002879\",\"WXM6R16C13002435\",\"WEY6R16B23002884\",\"HGEQU6GF\",\"X6GNU17B06101778\",\"X6GNU17B02111863\",\"X6GNU17B06102221\",\"X6GNU17B06101916\",\"47Q6R16B16000989\",\"X6GNU17B02113214\",\"X6GNU17B02110785\",\"X6GNU17B06102131\",\"X6GNU17B06100293\",\"X6GNU17B06103003\",\"HGEQU8NY\",\"X6GNU17B06100363\",\"WXM6R16C13002434\",\"WXM6R16C13000780\",\"HGEQU6H8\",\"X6GNU17B06102866\",\"HGEQU7WX\",\"X6GNU17B06101160\",\"X6GNU18319104037\",\"WXM6R16507002957\",\"WXM6R16C09004486\",\"X6GNU17B02103804\",\"HGES02XQ\",\"X6GNU17B06100865\",\"WXM6R16507002966\",\"DVLNU19312103891\",\"R22FC017R9Y\",\"X6GNU17B06101873\",\"47Q6R16B12001152\",\"X6GNU17B06102611\",\"X6GNU17B06100717\",\"WXM6R16C13002489\",\"47Q6R16B12001151\",\"X6GNU17B06101679\",\"X6GNU17B02110713\",\"HGEQU66R\",\"X6GNU17B06101094\",\"WEY6R16B23005353\",\"X6GNU17B06100011\",\"X6GNU18412105758\",\"X6GNU17B06102280\",\"HGEQU5HQ\",\"X6GNU17B02107248\",\"X6GNU17904107563\",\"X6GNU17B06102456\",\"WEY6R16A15002043\",\"HGEQU8CB\",\"WXM6R16C13001623\",\"WXM6R16C09004383\",\"WXM6R16C13000771\",\"HGEQU534\",\"X6GNU17B02111288\",\"HGEQU2TQ\",\"X6GNU17B06100197\",\"X6GNU17B02105101\",\"DVLNU19312104364\",\"WXM6R16C13000499\",\"X6GNU17B06100115\",\"X6GNU17B02109934\",\"WXM6R16C13002428\",\"WXM6R16507002936\",\"X6GNU17B02104522\",\"X6GNU17B02111000\",\"DVLNU19312104754\",\"DVLNU19312103844\",\"WXM6R16C13001617\",\"X6GNU17B02107351\",\"HGEQU4XW\",\"X6GNU17B02107813\",\"WEY6R16B25002603\",\"WXMDU17215001502\",\"WXM6R16B18011130\",\"X6GNU17B06100684\",\"WXM6R16C13001528\",\"X6GNU17B06100516\",\"WXM6R16C13000709\",\"X6GNU17B06101360\",\"DVLNU19312100412\",\"X6GNU17B06102018\",\"WXM6R16C09004386\",\"HGEQUA8K\",\"X6GNU18319105732\",\"DVLNU19312103296\",\"WXM6R16C13001690\",\"X6GNU17B06100810\",\"WXM6R17104001128\",\"DVLNU19312104290\",\"WXM6R16C13001606\",\"X6GNU17B06100177\",\"X6GNU17B06103068\",\"DVLNU19312103976\",\"WXM6R16C13002471\",\"X6GNU17B06102819\",\"X6GNU17B06102387\",\"WXM6R16C13001669\",\"X6GNU17B06101890\",\"WXM6R16B18010593\",\"HGES04WA\",\"X6GNU18412105959\",\"DVLNU19312100311\",\"HGES04CB\",\"WEY6R16B23002861\",\"WXM6R16C13001557\",\"X6GNU17B06100510\",\"HGEQUWBC\",\"WEY6R16B25000348\",\"DVLNU19312100118\",\"WXM6R16C13002499\",\"X6GNU17B02112952\",\"WXM6R16C13001708\",\"WXM6R16C13000760\",\"WXM6R16507000251\",\"HGEQU2TX\",\"X6GNU18412106630\",\"WXM6R16B18011159\",\"X6GNU17927118479\",\"X6GNU17B06100419\",\"X6GNU17B06100725\",\"X6GNU18412106898\",\"WEY6R16B23005399\",\"X6GNU17B06100904\",\"X6GNU17B02104664\",\"X6GNU17B06100406\",\"WXM6R16C13003182\",\"DVLNU19312104363\",\"WXM6R16C13000712\",\"WXM6R16B18009840\",\"47Q6R16B16000749\",\"WXM6R16C09004494\",\"HGES04A2\",\"WXM6R16C13001573\",\"X6GNU17B06102010\",\"X6GNU17B06101524\",\"DVLNU19312101865\",\"47Q6R16B12001150\",\"X6GNU18412106631\",\"X6GNU17B06102842\",\"WXM6R16C09004372\",\"HGEQU5K5\",\"X6GNU17B02106837\",\"X6GNU17B06100046\",\"X6GNU17B06102823\",\"DVLNU19312103284\",\"863736030012376\",\"X6GNU17B06101837\",\"WXM6R16C13001871\",\"X6GNU17B06102966\",\"DVLNU19312101832\",\"X6GNU17B06100439\",\"WXM6R16C13000535\",\"WXM6R16C13001742\",\"WXM6R16B18009798\",\"WEY6R16A15002285\",\"X6GNU17B06100705\",\"47Q6R16928001437\",\"HGES04X9\",\"WXM6R16C13002439\",\"HGES037V\",\"X6GNU17B06100195\",\"X6GNU18319106850\",\"X6GNU17B06102182\",\"DVLNU19312103892\",\"HGES04S4\",\"X6GNU17B06100583\",\"X6GNU17B06102205\",\"X6GNU17B06101027\",\"X6GNU17B02111334\",\"X6GNU17B06101238\",\"47Q6R16B12001163\",\"X6GNU17B06102306\",\"WXM6R16B18009869\",\"X6GNU17B06101826\",\"X6GNU17B06102568\",\"X6GNU17B06100981\",\"RV2G4014PYM\",\"WEY6R16B23002771\",\"HGEQU2XN\",\"WXM6R16C13000766\",\"WXM6R16A22000711\",\"X6GNU17B06100308\",\"X6GNU17B06101899\",\"X6GNU18319106386\",\"WXM6R16C13003125\",\"X6GNU17B06103168\",\"WXM6R16C13001603\",\"X6GNU17B06101289\",\"WXM6R16C13002500\",\"WXM6R16C09004421\",\"WXM6R16A22000945\",\"X6GNU17B06100772\",\"47Q6R16B12001162\",\"X6GNU17B06100104\",\"WXM6R16C13002533\",\"HGEQU256\",\"X6GNU17B06102911\",\"WXM6R16507002897\",\"X6GNU17B06100420\",\"WXM6R16B18009730\",\"WXM6R16C13000711\",\"X6GNU18412106423\",\"X6GNU17B06100451\",\"WXM6R16C13001601\",\"WXM6R16C13002548\",\"X6GNU17B06101527\",\"WXM6R16C09004491\",\"X6GNU17B06103034\",\"X6GNU17B06102144\",\"WXM6R16C13003075\",\"X6GNU17B02111685\",\"WXM6R16C13000768\",\"X6GNU17B06100281\",\"X6GNU18319105343\",\"DVLNU19312103901\",\"DVLNU19312103285\",\"DVLNU19312100136\",\"WXM6R16A22000736\",\"WXM6R16A22000873\",\"X6GNU17B06100846\",\"X6GNU17B06101911\",\"DVLNU19312103264\",\"DVLNU19312104335\",\"X6GNU17B06100456\",\"X6GNU17B06100124\",\"X6GNU17B06102549\",\"X6GNU18412105750\",\"47Q6R16B12001157\",\"WXM6R16507002961\",\"X6GNU18412106181\",\"X6GNU17B06101919\",\"WXM6R16A22000670\",\"WEY6R16B23003592\",\"X6GNU17B02108699\",\"DVLNU19312100290\",\"DVLNU19312100132\",\"X6GNU17B06100108\",\"X6GNU17B06102066\",\"47Q6R16202002202\",\"X6GNU17B06102503\",\"X6GNU17B06101531\",\"WXM6R16507002986\"]";


        Set<String> sets = new HashSet<>();

        try {
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                sets.add(array.optString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        String kkk = "DVLNU19312109425\n" +
                "DVLNU19312100136\n" +
                "DVLNU19312101743\n" +
                "DVLNU19312100099\n" +
                "DVLNU19312103961\n" +
                "DVLNU19312104760\n" +
                "DVLNU19312104559\n" +
                "DVLNU19312104363\n" +
                "DVLNU19312100118\n" +
                "DVLNU19312103937\n" +
                "DVLNU19305102335\n" +
                "DVLNU19312101820\n" +
                "DVLNU19312101870\n" +
                "DVLNU19312103318\n" +
                "DVLNU19312103831\n" +
                "DVLNU19312101764\n" +
                "DVLNU19312102453\n" +
                "DVLNU19312101722\n" +
                "DVLNU19312100412\n" +
                "DVLNU19312101702\n" +
                "DVLNU19312100247\n" +
                "DVLNU19312103264\n" +
                "DVLNU19312104669\n" +
                "DVLNU19312100444\n" +
                "DVLNU19312100150\n" +
                "DVLNU19312101809\n" +
                "DVLNU19312100105\n" +
                "DVLNU19312100290\n" +
                "DVLNU19312103335\n" +
                "DVLNU19312103934\n" +
                "DVLNU19312103901\n" +
                "DVLNU19312101865\n" +
                "DVLNU19312103285\n" +
                "DVLNU19312100311\n" +
                "DVLNU19312103892\n" +
                "DVLNU19312103284\n" +
                "DVLNU19312103902\n" +
                "DVLNU19312103277\n" +
                "DVLNU19312104335\n" +
                "DVLNU19312100132\n" +
                "DVLNU19312103921\n" +
                "DVLNU19312101872\n" +
                "DVLNU19312104754\n" +
                "DVLNU19312103976\n" +
                "DVLNU19312103941\n" +
                "DVLNU19312101687\n" +
                "DVLNU19312103891\n" +
                "DVLNU19312103900\n" +
                "DVLNU19312104290\n" +
                "DVLNU19312100128\n" +
                "DVLNU19312104364\n" +
                "DVLNU19312103308\n" +
                "DVLNU19312103844\n" +
                "DVLNU19312103298\n" +
                "DVLNU19312101832\n" +
                "DVLNU19312100422\n" +
                "DVLNU19312103893\n" +
                "DVLNU19312103238\n" +
                "DVLNU19312104381\n" +
                "DVLNU19312100313\n" +
                "DVLNU19312103296\n" +
                "DVLNU19312103849\n" +
                "DVLNU19312100107\n" +
                "DVLNU19312101742\n" +
                "DVLNU19312101872";


        String[] kkks = kkk.split("\n");

        sets.addAll(Arrays.asList(kkks));

        JSONArray array = new JSONArray();

        for (String s1 : sets) {
            array.put(s1);
        }


        Log.e("111111111111", array.toString());


        File f = new File(Environment.getExternalStorageDirectory() + File.separator + "uusafe", "sn.json");
        if (f.exists()) {
            FileUtils.delFileOrDir(f);
        }
        if (!f.exists()) {
            try {
                f.createNewFile();
                FileUtils.writeToFile(f, array.toString());
            } catch (IOException e) {
                e.printStackTrace();
                new String(new byte[3]);
            }
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    public void click13(View view) {

        List<SubscriptionInfo> list = SubscriptionManager.from(this).getActiveSubscriptionInfoList();
        for (SubscriptionInfo info : list) {
            System.out.println("ICCID-->" + info.getIccId());//898600810117F0001559
            System.out.println("subId-->" + info.getSubscriptionId());
            System.out.println("SimSlotIndex-->" + info.getSimSlotIndex());
            System.out.println("DisplayName-->" + info.getDisplayName());
            System.out.println("CarrierName-->" + info.getCarrierName());
            System.out.println("getNumber()-->" + info.getNumber());
            System.out.println("---------------------------------");

        }
    }


    WifiHotUtil wifiHotUtil;

    public void click14(View view) throws InterruptedException {

        wifiHotUtil = new WifiHotUtil(this);
        //创建热点
        wifiHotUtil.startWifiAp("aaaaa", "123456aA");

        Thread.sleep(3000);


        wifiHotUtil.close();
    }

    public void click25(View view) {


        ContentResolver resolver = getContentResolver();
        String[] projection = {
                CallLog.Calls.PHONE_ACCOUNT_ID,
//                "simid",
        };
        String sortOrder = BaseColumns._ID + " desc ";
        Cursor cursor = resolver.query(CallLog.Calls.CONTENT_URI, null, null, null, sortOrder);

        int count = cursor.getCount();
        int columnCount = cursor.getColumnCount();
        StringBuilder stringBuilder = new StringBuilder();
        long date22 = 0;
        while (cursor.moveToNext()) {
            JSONObject jsonObject = new JSONObject();
            for (int i = 0; i < columnCount; i++) {
                try {
                    jsonObject.put(cursor.getColumnName(i), cursor.getString(i));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

//                stringBuilder.append(cursor.getColumnName(i)+":"+cursor.getString(i)+" ");

            }
            date22 = cursor.getLong(cursor.getColumnIndex(CallLog.Calls.LAST_MODIFIED));
            System.out.println(jsonObject);
        }


        File srcFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/MIUI/sound_recorder/call_rec");
        if (!srcFile.isDirectory()) {
            Log.e(TAG, "/MIUI/sound_recorder/call_rec is not exists: ");
            return;
        }
        File[] files = srcFile.listFiles();
        if (files.length == 0) {
            Log.d(TAG, "/MIUI/sound_recorder/call_rec not have child file ");
            return;
        }
        File lastModifiedFile = files[0];
        for (File file : files) {
            if (file.lastModified() > lastModifiedFile.lastModified()) {
                lastModifiedFile = file;
            }
            System.out.println("当前时间跟文件最后修改时间: " + file.lastModified());
        }

        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        try {
            mmr.setDataSource(lastModifiedFile.getAbsolutePath());
            String strDuration = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            long duration = Long.valueOf(strDuration);

            long l = lastModifiedFile.lastModified() - duration;

            String name = lastModifiedFile.getName();
            System.out.println("录音名称： " + name);
            int i = name.lastIndexOf(".");
            String fileStartTime = name.substring(i - 14, i);
            long currentTime = System.currentTimeMillis() + 10 * 60 * 1000;
            Date date = new Date(currentTime);
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            String nowTime = df.format(date);
            Date date1 = df.parse(fileStartTime);
            long l1 = date1.getTime() - l;

//            System.out.println("时间差： " + l1);
            long currentTimeMillis = System.currentTimeMillis();
            long fileLastM = lastModifiedFile.lastModified();

//            System.out.println("当前时间跟文件最后修改时间: " +fileLastM);
//
//            long l2 = lastModifiedFile.lastModified() - date22;
//            System.out.println("lastmodyfiy时间差： " + l2);


        } catch (Exception e) {

        }


    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    public static Pair<Integer, Integer> getSlotIdAndSubId(Context context, String bundleIMSI) {
        SubscriptionManager manager = (SubscriptionManager) context.getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE);
        @SuppressLint("MissingPermission") List<SubscriptionInfo> list = manager.getActiveSubscriptionInfoList();
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (list == null || telephonyManager == null) {
            return null;
        }
        for (SubscriptionInfo subscriptionInfo : list) {
            try {
                int slotId = subscriptionInfo.getSimSlotIndex();
                int subId = subscriptionInfo.getSubscriptionId();
                Method m = telephonyManager.getClass().getDeclaredMethod("getSubscriberId", int.class);
                if (m == null) {
                    continue;
                }
                Object obj = m.invoke(telephonyManager, subId);
                if (obj == null) {
                    continue;
                }
                String imsi = obj.toString();
                if (!TextUtils.isEmpty(bundleIMSI) && bundleIMSI.equalsIgnoreCase(imsi)) {
                    return new Pair<>(slotId, subId);
                }

            } catch (Exception ignore) {
            }
        }
        return null;
    }


    private static final String SENT_SMS_ACTION = "SENT_SMS_ACTION";

    public void click27(View view) {

        Pair<Integer, Integer> pair = getSlotIdAndSubId(this, "460000463149413");
        Intent sentIntent = new Intent(SENT_SMS_ACTION);
//            sentIntent.putExtra("com.android.phone.extra.slot", 1);
        int subIdByIccid = getSubIdByIccid(this, "89860117811000900184");
//            SmsManager   smsManager = SmsManager.getSmsManagerForSubscriptionId(subIdByIccid);
        SmsManager smsManager = SmsManager.getDefault();


        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, sentIntent, 0);
        ArrayList<PendingIntent> sentIntents = new ArrayList<>();
        sentIntents.add(sentPI);
        ArrayList<String> contents = smsManager.divideMessage("我是信息内容4444");
        smsManager.sendMultipartTextMessage("18811452634", null, contents, sentIntents, null);

//        ServerSocket serverSocket =new ServerSocket("80");
//        Socket socket = serverSocket.accept();

    }

    public String getImsiFromSubId(int subId) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            Method m = telephonyManager.getClass().getDeclaredMethod("getSubscriberId", int.class);
            if (m == null) {
                return null;
            }
            Object obj = m.invoke(telephonyManager, subId);
            if (obj == null) {
                return null;
            }
            String imsi = obj.toString();
            return imsi;

        } catch (Exception ignore) {
            return null;
        }
    }


    /**
     * @param context
     * @param iccid
     * @return SubId  如果找不到匹配的sim卡  返回-1
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    public static int getSubIdByIccid(Context context, String iccid) {
        SubscriptionManager manager = (SubscriptionManager) context.getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE);
        @SuppressLint("MissingPermission") List<SubscriptionInfo> list = manager.getActiveSubscriptionInfoList();
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (list == null || telephonyManager == null) {
            return -1;
        }
        for (SubscriptionInfo subscriptionInfo : list) {
            try {
                int subscriptionId = subscriptionInfo.getSubscriptionId();
                if (!TextUtils.isEmpty(iccid) && iccid.equalsIgnoreCase(subscriptionInfo.getIccId())) {
                    return subscriptionId;
                }

            } catch (Exception ignore) {
            }
        }
        return -1;
    }

    public void click28(View view) {
        // TODO: 19-8-5 这个方法必须在emm调用    改签名的方法没试过
        setSendSmsDisabled(false);

    }


    Object mVivoCustService;

    private Object getVivoCustService() {
        try {
            if (this.mVivoCustService == null) {
                Class<?> vivoSpecService = Class.forName("com.vivo.services.cust.VivoCustomManager");
                if (vivoSpecService != null) {
                    this.mVivoCustService = vivoSpecService.newInstance();
                }
            }
        } catch (Throwable var2) {
            var2.printStackTrace();
        }

        return this.mVivoCustService;
    }

    public void setSendSmsDisabled(boolean disable) {
        try {
            Object o = this.getVivoCustService();
            if (o != null) {
                this.invoke(o, "setSendMmsDisabled", new Class[]{Boolean.TYPE}, disable);
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    private Object invoke(Object obj, String str, Class<?>[] clsArr, Object... objArr) throws Exception {
        Method declaredMethod = obj.getClass().getDeclaredMethod(str, clsArr);
        declaredMethod.setAccessible(true);
        return declaredMethod.invoke(obj, objArr);
    }

    public void click35(View view) {
        String imsiFromSubId = getImsiFromSubId(2);
        System.out.println("imsi: " + imsiFromSubId);
    }


int i;
    public void click42(View view) {


//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // 无ismi时使用默认
//        if (slotId != -1) {
            intent.putExtra(dualSimTypes[i], 0);
//        }
        Uri data = Uri.parse("tel:" + 10086);
        intent.setData(data);
        startActivity(intent);
        i++;
    }

    public void click53(View view) {
        wifiName(this);

    }

    /**
     * wifiName
     *  todo 获取的是bssid   不是 wifi mac地址
     * @param context
     * @return
     */
    public String wifiName(Context context) {
        try {
            WifiManager wifiMgr = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wifiMgr.getConnectionInfo();
            System.out.println(info);
            String bssid = info.getBSSID();
            //e8:ab:f3:9c:f6:42
            EditText viewById = findViewById(R.id.et_bssid);
            viewById.setText(bssid);
            System.out.println("--------------" + bssid);
            String macAddress = info.getMacAddress();
            return info != null ? info.getSSID() : null;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    public void click71(View view) {
        try {
            WifiManager wifiMgr = (WifiManager) this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wifiMgr.getConnectionInfo();
            System.out.println(info);
//            String bssid = info.getBSSID();
//            //e8:ab:f3:9c:f6:42
//            EditText viewById = findViewById(R.id.et_bssid);
//            viewById.setText(bssid);
//            System.out.println("--------------" + bssid);
            String macAddress = info.getMacAddress();
            System.out.println("--------------macAddress: " + macAddress);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void click110(View view) {
        try {
            WifiManager wifiMgr = (WifiManager) this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wifiMgr.getConnectionInfo();
            System.out.println(info);
//            String bssid = info.getBSSID();
            String ssid = info.getSSID();
            EditText viewById = findViewById(R.id.et_ssid);
            viewById.setText(ssid);
            System.out.println("--------------" + ssid);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
