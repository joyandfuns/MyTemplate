package com.pupu.temsetup

import com.android.tools.idea.wizard.template.*
import mvvmSetup

val mvvmSetupTemplate
    get() = template {
        revision = 2
        name = "pupu-mvvm-kit"
        description = "Creates a new MVVM architecture page."
        minApi = 21
        minBuildApi = 21
        category = Category.Other // Check other categories
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.FragmentGallery, WizardUiContext.MenuEntry,
                WizardUiContext.NewProject, WizardUiContext.NewModule)

        val packageNameParam = defaultPackageNameParameter
        val entityName = stringParameter {
            name = "Entity Name"
            default = "Sample"
            help = "The name of the entity class to create and use in Activity, Fragment, ViewModel"
            constraints = listOf(Constraint.NONEMPTY)
        }

        val layoutName = stringParameter {
            name = "Layout Name"
            default = "fragment_sample"
            help = "The name of the layout to create for the fragment"
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = { fragmentToLayout(entityName.value.toLowerCase()) }
        }

        widgets(
                TextFieldWidget(entityName),
                TextFieldWidget(layoutName),
                PackageNameWidget(packageNameParam)
        )

        recipe = { data: TemplateData ->
            mvvmSetup(
                    data as ModuleTemplateData,
                    packageNameParam.value,
                    entityName.value,
                    layoutName.value
            )
        }
    }

val defaultPackageNameParameter get() = stringParameter {
    name = "Package name"
    visible = { !isNewModule }
    default = "com.pupumall.customer"
    constraints = listOf(Constraint.PACKAGE)
    suggest = { packageName }
}