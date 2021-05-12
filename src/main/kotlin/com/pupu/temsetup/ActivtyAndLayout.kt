import com.android.tools.idea.wizard.template.ProjectTemplateData

fun getActivity(
        packageName: String,
        entityName: String
) = """package $packageName

import androidx.fragment.app.Fragment
import com.pupumall.customer.activity.NavContainerActivity

/**
 * Created by wurf
 * on 2021/5/11.
 * MVVM示例Activity
 */
//@Route(path = PuPuRouter.PUPUMALL_${entityName.toUpperCase()})
class ${entityName}Activity : NavContainerActivity() {

//    @Autowired(name = PuPuRouter.PUPUMALL_${entityName.toUpperCase()}_PARAM)
//    @JvmField
//    var orderId: String? = null

    override fun getFragment(): Fragment {
        return ${entityName}Fragment().apply {
            // 传参到Fragment
            arguments
        }
    }
}"""

fun getFragment(
        packageName: String,
        entityName: String,
        projectData: ProjectTemplateData
) = """package $packageName

import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import ${projectData.applicationPackage}.base.PuPuFragment2
import ${projectData.applicationPackage}.databinding.Fragment${entityName}Binding

/**
 * Created by wurf
 * on 2021/5/11.
 * MVVM示例Fragment
 */
class ${entityName}Fragment : PuPuFragment2<Fragment${entityName}Binding, ${entityName}ViewModel>() {

    companion object {
        const val DATA = "data"
        const val EXTRA = "extra"
    }
    
    override fun initArgument(savedInstanceState: Bundle?) {
        arguments?.let {
            it[DATA]
            it[EXTRA]
        }
    }

    override fun ${entityName}ViewModel.initBinding(lifecycle: LifecycleOwner, binding: Fragment${entityName}Binding) {
        sampleLiveData.observe(lifecycle, Observer {
            
        })
    }

    override fun Fragment${entityName}Binding.initViews(vm: ${entityName}ViewModel) {
    }
}"""

fun getViewModel(
        packageName: String,
        entityName: String,
        projectData: ProjectTemplateData
) = """package $packageName

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import ${projectData.applicationPackage}.base.PuPuViewModel2
import ${projectData.applicationPackage}.repository.${entityName}Repository

/**
 * Created by wurf
 * on 2021/5/11.
 * MVVM示例ViewModel
 */
class ${entityName}ViewModel(
        val repository: ${entityName}Repository,
        private val handle: SavedStateHandle
) : PuPuViewModel2() {
    
    /** 示例LiveData */
    val sampleLiveData = MutableLiveData<Int>()
    
    /**
     * 网络请求
     */
    private fun fetchData() {
        repository.fetchData()
                .observe(this, errorBlock = { _: Int, _: String?, _: Throwable ->
                    false
                }, onCompletion = {
                    
                }) {
                    sampleLiveData.value = 1
                }
    }
}"""

fun getFragmentLayout(
        packageName: String,
        entityName: String) = """<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">

    <data>
        <variable
            name="vm"
            type="${packageName}.${entityName}ViewModel" />
    </data>

    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent">

    </androidx.constraintlayout.widget.ConstraintLayout>
</layout>"""