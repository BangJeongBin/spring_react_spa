import "./globals.css";
import Header from './layout/Header';
import Nav from './layout/Nav';
import Footer from './layout/Footer';

export default function RootLayout({ children }) {
  return (
      <html lang="ko">
      <head>
            <meta charSet="utf-8"/>
            <meta name="viewport" content="width=device-width, initial-scale=1"/>
            <link rel="shortcut icon" src="./favicon.icon" type="image/x-icon" size="32x32"/>
            <title>SemiProject v1</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
                  integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
                  crossOrigin="anonymous"/>
            <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" rel="stylesheet"/>
        </head>
      <body>
          <div className="container">
              <div className="content-wrapper mx-auto">
                <Header />
                <Nav />
                {children}
                <Footer />
              </div>
          </div>
          <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossOrigin="anonymous"></script>
      </body>
      </html>
  );
}
