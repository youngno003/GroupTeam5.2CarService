using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace CoachServer
{
    /// <summary>
    /// Interaction logic for Login.xaml
    /// </summary>
    public partial class Login : Window
    {
        public ObservableCollection<User> userList { get; set; }
        public ObservableCollection<User> getUserList()
        {
            ObservableCollection<User> list = new ObservableCollection<User>();
            Dictionary<string, object> jsonTList;
            string json;
            json = FBGet("https://carservices-cb0bd.firebaseio.com/UserList.json");
            jsonTList = JsonConvert.DeserializeObject<Dictionary<string, object>>(json);
            foreach (var o in jsonTList)
            {
                User t = JsonConvert.DeserializeObject<User>(o.Value.ToString());
                t.id = o.Key;
                list.Add(t);
            }
            return list;
        }
        public Login()
        {
            InitializeComponent();

        }
        public static string FBGet(string url)
        {
            string result = "";
            var request = WebRequest.CreateHttp(url);
            request.Method = "GET";
            request.ContentType = "application/json";
            var response = request.GetResponse();
            result = (new StreamReader(response.GetResponseStream())).ReadToEnd();
            return result;
        }
        private void LoginBtn(object sender, RoutedEventArgs e)
        {
            bool isHave = false;
            userList = getUserList();
            foreach (var u in userList)
            {
                if (u.email == email.Text && u.pass == pass.Password && u.rule == "admin")
                {
                    isHave = true;
                    break;
                }
            }
            if(isHave == true)
            {
                MainWindow main = new MainWindow();
                main.Show();
                this.Close();
            }
            else
            {
                MessageBox.Show("Tài khoản không tồn tại");
            }
            
        }
    }
}
