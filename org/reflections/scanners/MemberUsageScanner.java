package org.reflections.scanners;

import org.reflections.*;
import javassist.expr.*;
import javassist.*;
import javassist.bytecode.*;
import org.reflections.util.*;
import java.util.*;

public class MemberUsageScanner extends AbstractScanner
{
    private /* synthetic */ ClassPool classPool;
    
    private ClassPool getClassPool() {
        if (this.classPool == null) {
            final String lIlIIlllIIlllII = (String)this;
            synchronized (this) {
                this.classPool = new ClassPool();
                ClassLoader[] lIlIIlllIIlllll = this.getConfiguration().getClassLoaders();
                if (lIlIIlllIIlllll == null) {
                    lIlIIlllIIlllll = ClasspathHelper.classLoaders(new ClassLoader[0]);
                }
                final short lIlIIlllIIllIlI = (Object)lIlIIlllIIlllll;
                final Exception lIlIIlllIIllIIl = (Exception)lIlIIlllIIllIlI.length;
                for (final ClassLoader lIlIIlllIlIIIII : lIlIIlllIIllIlI) {
                    this.classPool.appendClassPath((ClassPath)new LoaderClassPath(lIlIIlllIlIIIII));
                }
            }
        }
        return this.classPool;
    }
    
    void scanMember(final CtBehavior lIlIIllllIIIIII, final Store lIlIIlllIllllll) throws CannotCompileException {
        final String lIlIIllllIIIIlI = String.valueOf(new StringBuilder().append(lIlIIllllIIIIII.getDeclaringClass().getName()).append(".").append(lIlIIllllIIIIII.getMethodInfo().getName()).append("(").append(this.parameterNames(lIlIIllllIIIIII.getMethodInfo())).append(")"));
        lIlIIllllIIIIII.instrument((ExprEditor)new ExprEditor() {
            public void edit(final ConstructorCall llIIIlIIIlIIlIl) throws CannotCompileException {
                try {
                    MemberUsageScanner.this.put(lIlIIlllIllllll, String.valueOf(new StringBuilder().append(llIIIlIIIlIIlIl.getConstructor().getDeclaringClass().getName()).append(".<init>(").append(MemberUsageScanner.this.parameterNames(llIIIlIIIlIIlIl.getConstructor().getMethodInfo())).append(")")), llIIIlIIIlIIlIl.getLineNumber(), lIlIIllllIIIIlI);
                }
                catch (NotFoundException llIIIlIIIlIIlll) {
                    throw new ReflectionsException(String.valueOf(new StringBuilder().append("Could not find member ").append(llIIIlIIIlIIlIl.getClassName()).append(" in ").append(lIlIIllllIIIIlI)), (Throwable)llIIIlIIIlIIlll);
                }
            }
            
            public void edit(final FieldAccess llIIIlIIIIlllII) throws CannotCompileException {
                try {
                    MemberUsageScanner.this.put(lIlIIlllIllllll, String.valueOf(new StringBuilder().append(llIIIlIIIIlllII.getField().getDeclaringClass().getName()).append(".").append(llIIIlIIIIlllII.getFieldName())), llIIIlIIIIlllII.getLineNumber(), lIlIIllllIIIIlI);
                }
                catch (NotFoundException llIIIlIIIIllllI) {
                    throw new ReflectionsException(String.valueOf(new StringBuilder().append("Could not find member ").append(llIIIlIIIIlllII.getFieldName()).append(" in ").append(lIlIIllllIIIIlI)), (Throwable)llIIIlIIIIllllI);
                }
            }
            
            public void edit(final NewExpr llIIIlIIIllIlIl) throws CannotCompileException {
                try {
                    MemberUsageScanner.this.put(lIlIIlllIllllll, String.valueOf(new StringBuilder().append(llIIIlIIIllIlIl.getConstructor().getDeclaringClass().getName()).append(".<init>(").append(MemberUsageScanner.this.parameterNames(llIIIlIIIllIlIl.getConstructor().getMethodInfo())).append(")")), llIIIlIIIllIlIl.getLineNumber(), lIlIIllllIIIIlI);
                }
                catch (NotFoundException llIIIlIIIlllIIl) {
                    throw new ReflectionsException(String.valueOf(new StringBuilder().append("Could not find new instance usage in ").append(lIlIIllllIIIIlI)), (Throwable)llIIIlIIIlllIIl);
                }
            }
            
            public void edit(final MethodCall llIIIlIIIlIlllI) throws CannotCompileException {
                try {
                    MemberUsageScanner.this.put(lIlIIlllIllllll, String.valueOf(new StringBuilder().append(llIIIlIIIlIlllI.getMethod().getDeclaringClass().getName()).append(".").append(llIIIlIIIlIlllI.getMethodName()).append("(").append(MemberUsageScanner.this.parameterNames(llIIIlIIIlIlllI.getMethod().getMethodInfo())).append(")")), llIIIlIIIlIlllI.getLineNumber(), lIlIIllllIIIIlI);
                }
                catch (NotFoundException llIIIlIIIllIIII) {
                    throw new ReflectionsException(String.valueOf(new StringBuilder().append("Could not find member ").append(llIIIlIIIlIlllI.getClassName()).append(" in ").append(lIlIIllllIIIIlI)), (Throwable)llIIIlIIIllIIII);
                }
            }
        });
    }
    
    @Override
    public void scan(final Object lIlIIllllIlIIII, final Store lIlIIllllIIllll) {
        try {
            final CtClass lIlIIllllIlIllI = this.getClassPool().get(this.getMetadataAdapter().getClassName(lIlIIllllIlIIII));
            long lIlIIllllIIllIl = (Object)lIlIIllllIlIllI.getDeclaredConstructors();
            float lIlIIllllIIllII = lIlIIllllIIllIl.length;
            for (byte lIlIIllllIIlIll = 0; lIlIIllllIIlIll < lIlIIllllIIllII; ++lIlIIllllIIlIll) {
                final CtBehavior lIlIIllllIllIII = lIlIIllllIIllIl[lIlIIllllIIlIll];
                this.scanMember(lIlIIllllIllIII, lIlIIllllIIllll);
            }
            lIlIIllllIIllIl = (long)(Object)lIlIIllllIlIllI.getDeclaredMethods();
            lIlIIllllIIllII = lIlIIllllIIllIl.length;
            for (byte lIlIIllllIIlIll = 0; lIlIIllllIIlIll < lIlIIllllIIllII; ++lIlIIllllIIlIll) {
                final CtBehavior lIlIIllllIlIlll = lIlIIllllIIllIl[lIlIIllllIIlIll];
                this.scanMember(lIlIIllllIlIlll, lIlIIllllIIllll);
            }
            lIlIIllllIlIllI.detach();
        }
        catch (Exception lIlIIllllIlIlIl) {
            throw new ReflectionsException(String.valueOf(new StringBuilder().append("Could not scan method usage for ").append(this.getMetadataAdapter().getClassName(lIlIIllllIlIIII))), lIlIIllllIlIlIl);
        }
    }
    
    String parameterNames(final MethodInfo lIlIIlllIlIlIIl) {
        return Utils.join(this.getMetadataAdapter().getParameterNames(lIlIIlllIlIlIIl), ", ");
    }
    
    private void put(final Store lIlIIlllIllIlll, final String lIlIIlllIllIIIl, final int lIlIIlllIllIIII, final String lIlIIlllIllIlII) {
        if (this.acceptResult(lIlIIlllIllIIIl)) {
            this.put(lIlIIlllIllIlll, lIlIIlllIllIIIl, String.valueOf(new StringBuilder().append(lIlIIlllIllIlII).append(" #").append(lIlIIlllIllIIII)));
        }
    }
}
