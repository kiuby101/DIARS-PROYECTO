#include <GL/glut.h>
#include "texturas/RgbImage.h"

float camaraX = -20;
float camaraY = 20;
float camaraZ = 20;

float posLuzX = 0;
float posLuzY = 70;
float posLuzZ = 0;

float angulo = 0;

float giro = 1;
float giroBalon = 0;
float girox = 1;
float ladox=0;
float movx = 1;


GLuint texture[1];

void loadTextureFromFile(const char* filename, int index)
{
	RgbImage theTexMap(filename);
	glGenTextures(1, &texture[index]);
	glBindTexture(GL_TEXTURE_2D, texture[index]);
	glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
	glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
	glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
	glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
	glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, theTexMap.GetNumCols(), theTexMap.GetNumRows(), 0, GL_RGB, GL_UNSIGNED_BYTE, theTexMap.ImageData());

}

void cargarImagenes() {
	loadTextureFromFile("texturas/textura_balon.bmp", 0);
}

void iniciarVentana(int w, int h) {
	glViewport(0, 0, w, h);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluPerspective(80.0, (float)w / (float)h, 1, 100);
}

void iniciarLuces() {
	GLfloat light_ambient[] = { 0.1,0.1,0.1,1 };
	GLfloat light_diffuse[] = { 0.8,0.8,0.8,1 };
	GLfloat light_specular[] = { 0.5,0.5,0.5,1 };

	float reflejo[] = { 0,0,0,1 };
	float posicionLuz[] = { posLuzX,posLuzY,posLuzZ,1 };
	int sombra = 128;

	glEnable(GL_LIGHTING);
	glEnable(GL_LIGHT0);
	glEnable(GL_DEPTH_TEST);
	glEnable(GL_COLOR_MATERIAL);

	glLightModelfv(GL_LIGHT_MODEL_AMBIENT, light_ambient);
	glLightfv(GL_LIGHT0, GL_AMBIENT, light_ambient);
	glLightfv(GL_LIGHT0, GL_DIFFUSE, light_diffuse);
	glLightfv(GL_LIGHT0, GL_SPECULAR, light_specular);
	glColorMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE);
	glMaterialfv(GL_FRONT, GL_SPECULAR, reflejo);
	glMateriali(GL_FRONT, GL_SHININESS, sombra);
	glLightfv(GL_LIGHT0, GL_POSITION, posicionLuz);



	//Luz 1
	GLfloat light_ambient1[] = { 0.1,0.1,0.1,1 };
	GLfloat light_diffuse1[] = { 0.8,0.8,0.8,1 };
	GLfloat light_specular1[] = { 0.5,0.5,0.5,1 };
	float posicionLuz1[] = { -20,0,0,1 };
	float posicionLuz3[] = { 20,0,0,1 };
	float posicionLuz4[] = { 0,0,-20,1 };
	float posicionLuz5[] = { 0,0,20,1 };

	glEnable(GL_LIGHT1);
	glLightfv(GL_LIGHT1, GL_AMBIENT, light_ambient1);
	glLightfv(GL_LIGHT1, GL_DIFFUSE, light_diffuse1);
	glLightfv(GL_LIGHT1, GL_SPECULAR, light_specular1);
	glLightfv(GL_LIGHT1, GL_POSITION, posicionLuz1);

	glEnable(GL_LIGHT3);
	glLightfv(GL_LIGHT3, GL_AMBIENT, light_ambient1);
	glLightfv(GL_LIGHT3, GL_DIFFUSE, light_diffuse1);
	glLightfv(GL_LIGHT3, GL_SPECULAR, light_specular1);
	glLightfv(GL_LIGHT3, GL_POSITION, posicionLuz3);

	glEnable(GL_LIGHT4);
	glLightfv(GL_LIGHT4, GL_AMBIENT, light_ambient1);
	glLightfv(GL_LIGHT4, GL_DIFFUSE, light_diffuse1);
	glLightfv(GL_LIGHT4, GL_SPECULAR, light_specular1);
	glLightfv(GL_LIGHT4, GL_POSITION, posicionLuz4);

	glEnable(GL_LIGHT5);
	glLightfv(GL_LIGHT5, GL_AMBIENT, light_ambient1);
	glLightfv(GL_LIGHT5, GL_DIFFUSE, light_diffuse1);
	glLightfv(GL_LIGHT5, GL_SPECULAR, light_specular1);
	glLightfv(GL_LIGHT5, GL_POSITION, posicionLuz5);

	//Luz 2 - Spot
	GLfloat colorSpot[] = { 0.95,0.365,1,1 };
	float posicionLuz2[] = { -20,0,0,1 };
	float spot_direccion[] = { 1,0,0 };
	//glEnable(GL_LIGHT2);
	glLightfv(GL_LIGHT2, GL_DIFFUSE, colorSpot);
	glLightfv(GL_LIGHT2, GL_SPECULAR, colorSpot);
	glLightfv(GL_LIGHT2, GL_POSITION, posicionLuz2);
	glLightf(GL_LIGHT2, GL_SPOT_CUTOFF, 50);
	glLightfv(GL_LIGHT2, GL_SPOT_DIRECTION, spot_direccion);
	glLightf(GL_LIGHT2, GL_SPOT_EXPONENT, 1);
	glLightf(GL_LIGHT2, GL_CONSTANT_ATTENUATION, 1);

}

void timer(int t)
{
	glutPostRedisplay();
	glutTimerFunc(20, timer, 0);
}

void dibujarEjes() {
	glBegin(GL_LINES);
	glColor3ub(255, 0, 0);
	glVertex3f(0, 0, 0);
	glVertex3f(50, 0, 0);

	glColor3ub(0, 255, 0);
	glVertex3f(0, 0, 0);
	glVertex3f(0, 50, 0);

	glColor3ub(0, 0, 255);
	glVertex3f(0, 0, 0);
	glVertex3f(0, 0, 50);
	glEnd();
}

void piso() {
	glColor3ub(180, 180, 180);
	glutSolidCube(2);
}

void balon() {
	GLUquadric* quad;
	glEnable(GL_TEXTURE_2D);

	glPushMatrix();
	glColor3ub(255, 255, 255);
	quad = gluNewQuadric();
	glBindTexture(GL_TEXTURE_2D, texture[0]);
	gluQuadricTexture(quad, 1);
	gluSphere(quad, 3, 20, 20);
	glPopMatrix();
	glDisable(GL_TEXTURE_2D);
	glFlush();

}

void dibujar() {
	iniciarLuces();

	glClearColor(0, 0, 0, 0);
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();

	gluLookAt(camaraX, camaraY, camaraZ, 0, 0, 0, 0, 1, 0);

	//Representación Luz 0
	glPushMatrix();
	glTranslatef(posLuzX, posLuzY, posLuzZ);
	glColor3ub(255, 255, 255);
	glutSolidSphere(1, 50, 50);
	glPopMatrix();

	glPushMatrix();
	glRotated(angulo, 0, 1, 0);

	dibujarEjes();
	//piso
	glPushMatrix();
	glScaled(50, 1, 50);
	glTranslated(0, -5, 0);
	piso();
	glPopMatrix();

	giro += 1;
	//giro 1
	glPushMatrix();
	glColor3ub(255, 0, 0);
	glTranslated(-10, 0, 0);
	glRotated(giro, 1, 0, 0);
	glutSolidCube(5);
	glPopMatrix();

	//giro 2
	glPushMatrix();
	glTranslated(10, 0, 0);
	glRotated(giro, 1, 0, 0);
	glTranslated(10, 15, 0);
	glColor3ub(255, 0, 0);
	glutSolidCube(5);
	glPopMatrix();

	glPushMatrix();
	
	giroBalon -= 1;
	if (ladox < 0 || ladox > 47) {
		movx *= -1;
		
	}

	ladox += 0.1 * movx;
	glTranslated(ladox, 0, 0);

	glRotated(giroBalon, 0, 0, 1);
	balon();
	glPopMatrix();


	glPopMatrix();

	glutSwapBuffers();
}

void manejarTeclado(unsigned char key, int x, int y)
{
	switch (key)
	{
	case 'a':
		posLuzX -= 1;
		break;
	case 'd':
		posLuzX += 1;
		break;
	case 'w':
		posLuzY += 1;
		break;
	case 's':
		posLuzY -= 1;
		break;
	case 'z':
		posLuzZ -= 1;
		break;
	case 'x':
		posLuzZ += 1;
	}
	glutPostRedisplay();
}

void teclado_especial(int tecla, int x, int y) {
	switch (tecla)
	{
	case 100: //izquierda
		angulo -= 1;
		break;
	case 101: //arriba
		camaraY += 0.2;
		break;
	case 102: //derecha
		angulo += 1;
		break;
	case 103: //abajo
		camaraY -= 0.2;
	}
	glutPostRedisplay();
}

int main(int argc, char* argv[]) {
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_RGBA | GLUT_DEPTH | GLUT_DOUBLE);
	glutInitWindowPosition(0, 0);
	glutInitWindowSize(1600, 800);
	glutCreateWindow("Ventana de aplicación");
	cargarImagenes();
	glutReshapeFunc(iniciarVentana);
	glutDisplayFunc(dibujar);
	glutKeyboardFunc(manejarTeclado);
	glutSpecialFunc(teclado_especial);
	glutTimerFunc(0, timer, 0);
	glutMainLoop();
	return 0;
}