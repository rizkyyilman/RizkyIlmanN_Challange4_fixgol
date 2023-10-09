import com.catnip.rizkyilmann_challange4.data.CategoryDataSource
import com.catnip.rizkyilmann_challange4.data.database.datasource.ProductDataSource
import com.catnip.rizkyilmann_challange4.data.mapper.toProductList
import com.catnip.rizkyilmann_challange4.model.Category
import com.catnip.rizkyilmann_challange4.model.DetailMenu
import com.catnip.rizkyilmann_challange4.utils.ResultWrapper
import com.catnip.rizkyilmann_challange4.utils.proceed
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import java.util.concurrent.Flow

interface ProductRepository {
    fun getCategories(): List<Category>
    fun getProducts(): kotlinx.coroutines.flow.Flow<ResultWrapper<List<DetailMenu>>>
}

class ProductRepositoryImpl(
    private val productDataSource: ProductDataSource,
    private val dummyCategoryDataSource: CategoryDataSource
) : ProductRepository {

    override fun getCategories(): List<Category> {
        return dummyCategoryDataSource.getProductCategory()
    }

    override fun getProducts(): kotlinx.coroutines.flow.Flow<ResultWrapper<List<DetailMenu>>> {
        return productDataSource.getAllProducts().map { proceed { it.toProductList() } }.onStart {
            emit(ResultWrapper.Loading())
            delay(2000)
        }
    }
}

