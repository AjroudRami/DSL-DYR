package com.polytech.dsl.ssl.dsl

import org.codehaus.groovy.control.CompilerConfiguration
import org.codehaus.groovy.control.customizers.SecureASTCustomizer

class GroovyDSL {

    private GroovyShell shell
    private CompilerConfiguration configuration
    private GroovyBinding binding

    GroovyDSL() {

        binding = new GroovyBinding()
        // binding.setGroovyModel(new GroovyModel(binding));
        configuration = getDSLConfiguration()
        configuration.setScriptBaseClass("main.groovy.groovuinoml.dsl.GroovyBasescript")
        shell = new GroovyShell(configuration)
    }

    private static CompilerConfiguration getDSLConfiguration() {
        def secure = new SecureASTCustomizer()
        secure.with {
            //disallow closure creation
            closuresAllowed = false
            //disallow method definitions
            methodDefinitionAllowed = true
            //empty white list => forbid imports
            importsWhitelist = [
                    'java.lang.*'
            ]
            staticImportsWhitelist = []
            staticStarImportsWhitelist= []
            //language tokens disallowed
            //tokensBlacklist= []
            //language tokens allowed
            tokensWhitelist= []
            //types allowed to be used  (including primitive types)
            constantTypesClassesWhiteList= [
                    int, Integer, Number, Integer.TYPE, String, Object
            ]
            //classes who are allowed to be receivers of method calls
            receiversClassesWhiteList= [
                    int, Number, Integer, String, Object
            ]
        }

        def configuration = new CompilerConfiguration()
        configuration.addCompilationCustomizers(secure)

        return configuration
    }

    void eval(File scriptFile) {
        Script script = shell.parse(scriptFile)

        binding.setScript(script)
        script.setBinding(binding)

        script.run()
    }
}
