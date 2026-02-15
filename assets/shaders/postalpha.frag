// Postalpha shader for Tranqol
precision mediump float;
varying vec2 v_texCoords;
uniform sampler2D u_texture;

void main() {
    vec4 color = texture2D(u_texture, v_texCoords);
    // Simple alpha pass
    gl_FragColor = color;
}