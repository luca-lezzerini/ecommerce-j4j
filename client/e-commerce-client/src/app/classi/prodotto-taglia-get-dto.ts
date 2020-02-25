import { LoginResponseDto } from './login-response-dto';
import { Prodotto } from './prodotto';
import { Taglia } from './taglia';

export class ProdottoTagliaGetDto extends LoginResponseDto{
  taglia: Taglia;
  prodotto: Prodotto;
}
