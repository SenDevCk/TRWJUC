import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bih.nic.bsphcl.trwjuc.fragments.Tab1Fragment
import com.bih.nic.bsphcl.trwjuc.fragments.Tab2Fregment
import com.bih.nic.bsphcl.trwjuc.fragments.Tab3Fregment

class MyPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 3 // Number of tabs

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> Tab1Fragment()
            1 -> Tab2Fregment()
            else -> Tab3Fregment()
        }
    }
}
