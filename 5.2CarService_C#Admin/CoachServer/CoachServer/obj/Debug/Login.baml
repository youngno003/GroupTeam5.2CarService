   M S B A M L   `   `   `  ����F  BCoachServer, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null)clr-namespace:CoachServerCoachServer  5   	       CoachServer.Login   5   	   \ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35U QWPFToolkit, Version=3.5.40128.1, Culture=neutral, PublicKeyToken=31bf3856ad364e35F 9http://schemas.microsoft.com/winfx/2006/xaml/presentation     5   	   R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   @d2http://schemas.microsoft.com/expression/blend/2008     5   	   Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5   	   +localclr-namespace:CoachServer     5   	     � 
ResizeMode$  NoResize=�5   	    � Title$ Login��5	   	   $	��190��6   $	��400��6$   .��5
      �  � ColumnDefinitions 5   
   �� 5      $
��auto �6   6.   �� 5      6!    � RowDefinitions 5   
   �� 5      $��60 �6   6*   �� 5      $��50 �6   6*   �� 5      6   .+�5   
   �� $��0, 15, 10, 5q�6   $��Tên đăng nhập:��6+   6C   �� 5   
   -   5        $��email��5   (   $��0, 15, 0, 5q�6   $��1��65   6G   �� 5   
   $��0, 5, 10, 5q�6   $��1��6)   $��Mật khẩu:��66   6I   G� 5   
   -   5        $
��pass��5   *   $��
0, 5, 0, 5q�6   $��1��66   $��1��6F   6U   �� 5   
   -   5        ���#0095ff$��2��5   &   $��2��6:   .��6X   Đăng nhập6c   5      5      