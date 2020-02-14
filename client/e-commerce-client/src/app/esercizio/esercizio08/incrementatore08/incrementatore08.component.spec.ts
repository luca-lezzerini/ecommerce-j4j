import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Incrementatore08Component } from './incrementatore08.component';

describe('Incrementatore08Component', () => {
  let component: Incrementatore08Component;
  let fixture: ComponentFixture<Incrementatore08Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Incrementatore08Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Incrementatore08Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
