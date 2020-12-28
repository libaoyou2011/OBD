package com.baolong.obd.component.extrat.assist;

//import android.support.design.widget.BottomSheetDialogFragment;

//import com.hzx.huanping.component.R.id;
//import com.hzx.huanping.component.R.layout;
//import com.hzx.huanping.component.R.string;


public class PermissionFragment
//        extends BottomSheetDialogFragment
//        implements EasyPermissions.PermissionCallbacks
{
//    private static final int RC = 256;
//    static PermissionFragment permissionFragment;
//
//    public static boolean haveAll(Context paramContext, FragmentManager paramFragmentManager) {
//        boolean bool2 = haveNetworkPerm(paramContext);
//        boolean bool1 = true;
//        if ((!bool2) || (!haveReadPerm(paramContext)) || (!haveWritePerm(paramContext))) {
//            bool1 = false;
//        }
//        if (!bool1) {
//            show(paramFragmentManager);
//        }
//        return bool1;
//    }
//
//    private static boolean haveNetworkPerm(Context paramContext) {
//        return EasyPermissions.hasPermissions(paramContext, new String[]{"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE", "android.permission.ACCESS_WIFI_STATE"});
//    }
//
//    private static boolean haveReadPerm(Context paramContext) {
//        return EasyPermissions.hasPermissions(paramContext, new String[]{"android.permission.READ_EXTERNAL_STORAGE"});
//    }
//
//    private static boolean haveRecordAudioPerm(Context paramContext) {
//        return EasyPermissions.hasPermissions(paramContext, new String[]{"android.permission.RECORD_AUDIO"});
//    }
//
//    private static boolean haveWritePerm(Context paramContext) {
//        return EasyPermissions.hasPermissions(paramContext, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"});
//    }
//
//    private void refreshState(View paramView) {
//        if (paramView == null) {
//            return;
//        }
//        Context localContext = getContext();
//        View localView = paramView.findViewById(R.id.im_state_permission_network);
//        boolean bool = haveNetworkPerm(localContext);
//        int i;
//        int j = 8;
//        if (bool) {
//            i = 0;
//        } else {
//            i = 8;
//        }
//        localView.setVisibility(i);
//        localView = paramView.findViewById(R.id.im_state_permission_read);
//        if (haveReadPerm(localContext)) {
//            i = 0;
//        } else {
//            i = 8;
//        }
//        localView.setVisibility(i);
//        paramView = paramView.findViewById(R.id.im_state_permission_write);
//        i = j;
//        if (haveWritePerm(localContext)) {
//            i = 0;
//        }
//        paramView.setVisibility(i);
//    }
//
//    @AfterPermissionGranted(256)
//    private void requestPerm() {
//        String[] arrayOfString = new String[5];
//        arrayOfString[0] = "android.permission.INTERNET";
//        arrayOfString[1] = "android.permission.ACCESS_NETWORK_STATE";
//        arrayOfString[2] = "android.permission.ACCESS_WIFI_STATE";
//        arrayOfString[3] = "android.permission.READ_EXTERNAL_STORAGE";
//        arrayOfString[4] = "android.permission.WRITE_EXTERNAL_STORAGE";
//        if (EasyPermissions.hasPermissions(getContext(), arrayOfString)) {
//            ToastUtils.toast(getString(R.string.label_permission_ok));
//            refreshState(getView());
//            if (permissionFragment != null) {
//                permissionFragment.dismiss();
//                permissionFragment = null;
//            }
//        } else {
//            EasyPermissions.requestPermissions(this, getString(R.string.title_assist_permissions), 256, arrayOfString);
//        }
//    }
//
//    private static void show(FragmentManager paramFragmentManager) {
//        if (permissionFragment == null) {
//            permissionFragment = new PermissionFragment();
//        }
//        permissionFragment.show(paramFragmentManager, PermissionFragment.class.getName());
//    }
//
//    @NonNull
//    public Dialog onCreateDialog(Bundle paramBundle) {
//        return new TransStatusBottomSheetDialog(getContext());
//    }
//
//    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
//        View view = paramLayoutInflater.inflate(R.layout.fragment_permission, paramViewGroup, false);
//        view.findViewById(R.id.btn_submit).setOnClickListener(new View.OnClickListener() {
//            public void onClick(View paramAnonymousView) {
//                PermissionFragment.this.requestPerm();
//            }
//        });
//        return view;
//    }
//
//    public void onPermissionsDenied(int paramInt, @NonNull List<String> paramList) {
//        if (EasyPermissions.somePermissionPermanentlyDenied(this, paramList)) {
//            new AppSettingsDialog.Builder(this).setTitle(R.string.recorder_permission).setRationale(R.string.recorder_permission_change).build().show();
//        }
//    }
//
//    public void onPermissionsGranted(int paramInt, @NonNull List<String> paramList) {
//    }
//
//    public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt) {
//        super.onRequestPermissionsResult(paramInt, paramArrayOfString, paramArrayOfInt);
//        EasyPermissions.onRequestPermissionsResult(paramInt, paramArrayOfString, paramArrayOfInt, new Object[]{this});
//    }
//
//    public void onResume() {
//        super.onResume();
//        refreshState(getView());
//    }
}
