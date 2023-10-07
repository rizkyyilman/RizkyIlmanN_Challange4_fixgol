import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.catnip.rizkyilmann_challange4.model.DetailMenu

class DetailViewModel : ViewModel() {
    private val _detailMenu = MutableLiveData<DetailMenu>()
    val detailMenu: LiveData<DetailMenu>
        get() = _detailMenu

    fun setDetailMenu(menu: DetailMenu) {
        _detailMenu.value = menu
    }
}
