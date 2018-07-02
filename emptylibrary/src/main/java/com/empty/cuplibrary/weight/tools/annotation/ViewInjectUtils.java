package com.empty.cuplibrary.weight.tools.annotation;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class ViewInjectUtils
{
	private static final String METHOD_SET_CONTENTVIEW = "setContentView";
	private static final String METHOD_FIND_VIEW_BY_ID = "findViewById";

	public static void inject(Activity activity)
	{
		injectContentView(activity);
		injectViews(activity);
		viewBindOnClick(activity);
	}


	/**
	 * 注入所有的控件
	 * 
	 * @param activity
	 */
	private static void injectViews(Activity activity)
	{
		Class<? extends Activity> clazz = activity.getClass();
		Field[] fields = clazz.getDeclaredFields();
		// 遍历所有成员变量
		for (Field field : fields)
		{
			ViewInject viewInjectAnnotation = field
					.getAnnotation(ViewInject.class);
			if (viewInjectAnnotation != null)
			{
				int viewId = viewInjectAnnotation.value();
				if (viewId != -1)
				{
					// 初始化View
					try
					{
						Method method = clazz.getMethod(METHOD_FIND_VIEW_BY_ID,
								int.class);
						Object resView = method.invoke(activity, viewId);
						field.setAccessible(true);
						field.set(activity, resView);
					} catch (Exception e)
					{
						e.printStackTrace();
					}

				}
			}

		}

	}

	/**
	 * 注入主布局文件
	 * 
	 * @param activity
	 */
	private static void injectContentView(Activity activity)
	{
		Class<? extends Activity> clazz = activity.getClass();
		// 查询类上是否存在ContentView注解
		ContentView contentView = clazz.getAnnotation(ContentView.class);
		if (contentView != null)// 存在
		{
			int contentViewLayoutId = contentView.value();
			try
			{
				Method method = clazz.getMethod(METHOD_SET_CONTENTVIEW,
						int.class);
				method.setAccessible(true);
				method.invoke(activity, contentViewLayoutId);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * 注入控件 控件监听绑定
	 * @param activity
	 */
	private static void viewBindOnClick(final Activity activity){
		//获取Class 对象
		Class<? extends Activity> clazz = activity.getClass();
		//根据class 获取 方法(这是所有方法)
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method:methods){
			//获取方法里面 对应得注解 只要是ViewBtnOnclick 注解就进行控件监听
			ViewOnClick annotationOnClick = method.getAnnotation(ViewOnClick.class);
			if (null != annotationOnClick){
				//能获取到数据 就获取 注解里面的值
				int[] onClicBtnName = annotationOnClick.value();
				if (onClicBtnName.length > 0){
					//当里面有值得时候才进行绑定控件
					for (final int viewOnClick:onClicBtnName){
						if (viewOnClick == -1){
							return;
						}

						//获取到注解里面控件得 ID
						try {
							//获取对应得 方法  传入参数为 类型 （可以写成 null）
							final Method methodOnClickName = clazz.getMethod(method.getName(),int.class);

							methodOnClickName.setAccessible(true);
							View view = activity.findViewById(viewOnClick);
							view.setOnClickListener(new View.OnClickListener() {
								@Override
								public void onClick(View view) {
									//通过 activity 调用 上面这个方法名称 传入 viewOnClick
									try {
										methodOnClickName.invoke(activity,viewOnClick);

									} catch (IllegalAccessException e) {
										e.printStackTrace();
									} catch (InvocationTargetException e) {
										e.printStackTrace();
									}
								}
							});

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}


	}
}
