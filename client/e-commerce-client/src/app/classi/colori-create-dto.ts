import { Colori } from './colori';
import { LoginResponseDto } from './login-response-dto';

export class ColoriCreateDto extends LoginResponseDto {
  dati: Colori;
}
